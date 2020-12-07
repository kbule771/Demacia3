from django.shortcuts import render, get_object_or_404
from rest_framework.response import Response
from rest_framework.decorators import api_view
from .serializers import *
from .models import *
from django.contrib.auth.decorators import login_required
import requests
import json
from drf_yasg.utils import swagger_auto_schema
from drf_yasg import openapi
from django.views.decorators.csrf import csrf_exempt
from django.utils.decorators import method_decorator
from .video import get_image, change_text, timeline, winrate_algo
# Create your views here.
# .
@api_view(['GET'])
def recommand_champion(request,userno):
    champions = Match.objects.filter(userno=userno)#{'티모':0.7,'아리':0.6,'럭스':0.3}
    serializers = MatchSerializer(champions, many=True) 
    # 비슷한 성향을 가진 챔들
    # step1 챔키로 해당 mbti 구하기
    user_champions = Match.objects.filter(userno=userno).values('recommand_champion')[0]['recommand_champion']
    json_champions = user_champions.replace("'","\"")

    groups = list(json.loads(json_champions).keys())
    chams_mbti = []
    similar_cham_recommand = dict()
    for key in groups:
        cham_mbti = Champion.objects.filter(chamkey=int(key)).values('mbti')[0]['mbti']
        similar_cham = list(Champion.objects.filter(mbti=cham_mbti).order_by("-winning_rate").values("chamkey")[:3])
        group = [str(similar_cham[0]["chamkey"]),str(similar_cham[1]["chamkey"]),str(similar_cham[2]["chamkey"])]
        similar_cham_recommand.setdefault(key,0)
        similar_cham_recommand[key] = group
    resonse_data = {
        'recommand_cham_info': serializers.data,
        'similar_cham_recommand': [similar_cham_recommand]
    }


    # ['ENTP', 'ESTJ', 'ESFJ']
    
    return Response(resonse_data)

@api_view(['GET'])
def recommand_group(request,userno):
    champions = Match.objects.filter(userno=userno).values('recommand_champion')[0]['recommand_champion'] #{'티모':0.7,'아리':0.6,'럭스':0.3}
    json_champions = champions.replace("'","\"")
    groups = list(json.loads(json_champions).keys())
    if len(groups) > 0:
        group1 = Champion.objects.filter(chamkey=int(groups[0]))
    else:
        group1 = []
    if len(groups) > 1:
        group2 = Champion.objects.filter(chamkey=int(groups[1]))
    else:
        group2 = []
    if len(groups) > 2:
        group3 = Champion.objects.filter(chamkey=int(groups[2]))
    else:
        group3 = []

    response_data = {
        'First_cham': ChampionSerializer(group1, many=True).data,
        'Second_cham': ChampionSerializer(group2, many=True).data,
        'Third_cham' : ChampionSerializer(group3, many=True).data,
    }

    return Response(response_data)

@api_view(['GET'])
def champion_list(request):
    champions = Champion.objects.all() 
    serializers = ChampionSerializer(champions, many=True)
    return Response(serializers.data)

@api_view(['GET'])
def videopost_list(request,videopostno):
    videoposts = Videopost.objects.filter(videopostno=videopostno)
    serializers = VideopostSerializer(videoposts, many=True)
    return Response(serializers.data)

@swagger_auto_schema(method='post', request_body=VideoUpdateSerializer)
@api_view(['POST'])
def videopost_update(request):
    request_video = request.data['video']
    # print(request_video)
    # videoname = Videopost.objects.filter(video=request_videopostno).values('video').distinct()[0]['video']
    gameId = get_image.get_image(request_video)
    new_time,gameId = change_text.change_text(gameId)
    time_part_set, new_part_set, gameId = timeline.timeline(new_time,gameId)
    dict_result = winrate_algo.winrate_algo(time_part_set, new_part_set, gameId)
    result = [dict_result]
    #videodata = Videopost.objects.filter(video=request_video).update(data = result)
    #videos = Videopost.objects.filter(video=request_video)
    #serializers = VideopostSerializer(videos, many=True)
    #return Response(serializers.data)
    return Response(result)

@api_view(['POST'])
def match_update(request):
    request_userno = request.data['userno']
    matchdata = Match.objects.filter(userno=request_userno)

    game_ids = []
    lane_dict = {}
    champion_info = dict()

    account_id = User.objects.filter(userno=request_userno).values('accountid')[0]["accountid"]
    if account_id != None:
        print(account_id)
        match_v4_accountid = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+str(account_id)+"?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
        account_results = requests.get(match_v4_accountid).json()["matches"]
        # print(results)

        cnt = 0
        for i in range(len(account_results)):
            if cnt == 80:
                break
            game_ids.append(account_results[i]["gameId"])
            # lane dict 만들기
            lane = account_results[i]['lane']
            lane_dict.setdefault(lane, 0)
            lane_dict[lane] += 1
            cnt += 1
        game_ids = list(set(game_ids))
        # print(game_ids)
        # print(lane_dict)

        for game_id in game_ids:
            match_v4_gameId ="https://kr.api.riotgames.com/lol/match/v4/matches/"+str(game_id)+"?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
            game_results = requests.get(match_v4_gameId).json()
            # participantId 얻기
            for parts in game_results["participantIdentities"]:
                if parts["player"]["accountId"] == account_id:
                    partId = parts["participantId"]
                    break
            
            # character 정보, 이겼는지 졌는지 표시
            for part in game_results["participants"]:
                if part["participantId"] == partId:
                    chamId = part["championId"]
                    champion_info.setdefault(chamId, [0, 0])
                    if part["stats"]["win"] == True:
                        champion_info[chamId][0] += 1
                    else:
                        champion_info[chamId][1] += 1
                    break

        # print(champion_info)

        champion_winnig_rate = dict()
        real_cham_dict = dict()
        for champion_id in champion_info:
            champion_winnig_rate[champion_id] = champion_info[champion_id][0] / sum(champion_info[champion_id])

        champion_winnig_rate = sorted(champion_winnig_rate.items(), key=lambda x:x[1], reverse=True)

        for idx in range(len(champion_winnig_rate)):
            if idx > 2:
                break
            else:
                real_cham_dict[str(champion_winnig_rate[idx][0])] = str(champion_winnig_rate[idx][1])

        ### 승률 dict
        print(real_cham_dict)

        ### 최고 lane
        lane_dict = sorted(lane_dict.items(), key=lambda x:x[1], reverse=True)
        lane = lane_dict[0][0]

        # real_cham_dict = {'53': '1.0', '223': '1.0', '3': '1.0'}
        # lane = "MID"


        # 전적 기록이 아직 없으면 save()
        if len(matchdata) == 0:
            new_match = Match.objects.create(userno=User.objects.get(userno=request_userno), recommand_champion=real_cham_dict, lane=lane)
        else:
            matchdata.update(recommand_champion=real_cham_dict, lane=lane)

        matches = Match.objects.filter(userno=request_userno)
        serializers = MatchSerializer(matches, many=True)
        return Response(serializers.data)

    else:
        return 'accountid 없음'

@api_view(['GET'])
def match_list(request,userno):
    matches = Match.objects.filter(userno=userno)
    serializers = MatchSerializer(matches, many=True)
    return Response(serializers.data)


@swagger_auto_schema(method='post', request_body=UserSerializer)
@api_view(['POST'])
def mbti_update(request):
    request_userno = request.data['userno']
    request_mbti = request.data['mbti']
    Userdata = User.objects.filter(userno=request_userno).update(mbti=request_mbti)
    serializers = UserSerializer(data=request.data)
    if serializers.is_valid():
        return Response(serializers.data)


@api_view(['GET'])
def mbti_cham(request, user_mbti):
    mbti_chams = Champion.objects.filter(mbti=user_mbti)

    good_mbti = Champion.objects.filter(mbti=user_mbti).values('goodmbti').distinct()[0]['goodmbti']
    good_chams = Champion.objects.filter(mbti=good_mbti)

    bad_mbti = Champion.objects.filter(mbti=user_mbti).values('badmbti').distinct()[0]['badmbti']
    bad_chams = Champion.objects.filter(mbti=bad_mbti)

    mbti_serializers = ChampionSerializer(mbti_chams, many=True)
    bad_serializers = ChampionSerializer(bad_chams, many=True)
    good_serializers = ChampionSerializer(good_chams, many=True)

    response_data = {
        'mbti_chams': mbti_serializers.data,
        'bad_chams': bad_serializers.data,
        'good_chams': good_serializers.data
    }

    return Response(response_data)


@login_required
@api_view(['GET'])
def replylike_list(request):
    replylikes = Replylikes.objects.all()
    serializers = ReplylikesSerializer(replylieks, many=True)
    return Response(serializers.data)

@login_required
@api_view(['POST','DELETE'])
def replylike_create_and_delete(request):
    replylikes = Replylikes.objects.all()
    serializers = ReplylikesSerializer(replylieks, many=True)
    return Response(serializers.data)


@login_required
@api_view(['GET'])
def videopostlike_list(request):
    videopostlikes = Videopostlikes.objects.all()
    serializers = VideopostlikesSerializer(videopostlikes, many=True)
    return Response(serializers.data)

@login_required
@api_view(['POST','DELETE'])
def videopostlike_create_and_delete(request):
    videopostlikes = Videopostlikes.objects.all()
    serializers = VideopostlikesSerializer(videopostlikes, many=True)
    return Response(serializers.data)




