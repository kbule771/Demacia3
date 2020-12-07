<template>
  <div style="font-family: Tmon">
    <Carousel />
    <!-- <b-row class="maintitle text-center">
      <img src="../assets/img/damacia.png" alt="" style="display:block; margin:100px auto 0px">
      <b-col cols="12" class="sub pb-4 mb-4">
        <img src="../assets/img/subtitle.png" alt="" style="display:block; margin:0px auto 50px">
      </b-col>
    </b-row> -->
    <div class="container p-4">
      <img
        src="../assets/img/recommtitle.png"
        alt=""
        style="margin: 50px 0px 0px"
      />
      <b-row class="part" align-h="between">
        <b-col cols="12" md="6">
          <div class="box m-2 p-2 text-center">
            <br />
            <h4 style="text-align: center">나랑 어울리는 꿀조합이</h4>
            <h4 style="text-align: center">궁금하다면?</h4>
            <router-link
              :to="{ name: constants.URL_TYPE.RECOMMEND.COMBINATION }"
            >
              <b-button
                size="lg"
                class="mr-2 mt-3 hoverbtn"
                @click="callmatch()"
                style="
                  color: white;
                  background: linear-gradient(180deg, #1b2831 0%, #12384b 100%);
                  border-style: ridge;
                  border: #95ede7 3px ridge;
                "
              >
                조합 추천 보러가기
              </b-button>
            </router-link>
          </div>
        </b-col>
        <b-col cols="12" md="6">
          <div class="box m-2 p-2 text-center">
            <br />
            <h4 style="text-align: center">내가 가장 잘하는 챔피언이</h4>
            <h4 style="text-align: center">궁금하다면?</h4>
            <router-link :to="{ name: constants.URL_TYPE.RECOMMEND.CHAMPION }">
              <b-button
                size="lg"
                class="mr-2 mt-3 hoverbtn"
                @click="callmatch()"
                style="
                  color: white;
                  background: linear-gradient(180deg, #1b2831 0%, #12384b 100%);
                  border-style: ridge;
                  border: #95ede7 3px ridge;
                "
              >
                챔피언 추천 보러가기
              </b-button>
            </router-link>
          </div>
        </b-col>

        <b-col cols="12">
          <router-link :to="{ name: constants.URL_TYPE.VIDEO.MAIN }">
            <img
              src="../assets/img/videotittle.png"
              alt=""
              style="margin: 50px 0px 0px"
            />
          </router-link>
          <!-- 영상분석 글 -->
          <b-row align-h="center">
            <b-col
              cols="12"
              md="4"
              v-for="(item, $index) in lastVideo"
              :key="$index"
            >
              <!-- 리스트 시작 -->
              <b-row
                id="hhh"
                class="box text-left"
                style="margin-top: 10px; margin-bottom: 5%; cursor: Pointer"
                align-h="between"
                @click="toResult(item.videoPostNo)"
              >
                <b-col cols="12" class="m-0 p-2">
                  <b-img
                    id="sizepadding"
                    :src="thumbURL(item)"
                    :alt="item.videoPostNo + '번째영상썸네일'"
                    class="rounded w-100 h-100"
                    @error="noimage"
                  />
                </b-col>
                <b-col cols="6">
                  <div>
                    <b-avatar variant="secondary" :src="uImgURL(item)" />
                    {{ item.userNickname }}
                  </div>
                </b-col>
                <b-col cols="6">
                  <h5 class="text-right mt-1">
                    <b-icon-hand-thumbs-up />
                    {{ item.totalLike }}
                    <b-icon-caret-right-square-fill />
                    {{ item.view }}
                    <b-icon icon="lock-fill" v-if="item.isPrivate" />
                  </h5>
                </b-col>
                <b-col cols="12" class="pt-0"
                  >의뢰번호 No. {{ item.videoPostNo }}</b-col
                >
              </b-row>
            </b-col>
          </b-row>
        </b-col>
        <b-col cols="12">
          <router-link :to="{ name: constants.URL_TYPE.POST.MAIN }">
            <img
              src="../assets/img/votetitle.png"
              alt=""
              style="margin: 50px 0px 0px"
            />
          </router-link>
          <div class="box">
            <b-table
              hover
              striped
              :items="lastPost"
              :fields="lastPostFields"
              v-if="lastPost"
              table-variant="dark"
              style="color: #e3d19e"
            >
              <template v-slot:cell(postDate)="data">{{
                postDT(data.value)
              }}</template>
              <template v-slot:cell(to)="data">
                <router-link :to="'/detail/' + data.item.postNo">
                  <b-icon
                    icon="arrow-right-circle"
                    font-scale="2"
                    style="color: #e3d19e"
                  ></b-icon>
                </router-link>
              </template>
            </b-table>
            <div v-else>진행중인 투표글이 없습니다.</div>
          </div>
        </b-col>

        <b-col cols="12">
          <a href="/welcome">
            <img
              src="../assets/img/aboutus.png"
              alt=""
              style="display: block; margin: 100px auto 50px"
            />
          </a>
        </b-col>
      </b-row>
    </div>
  </div>
</template>
<script>
import Carousel from "../components/common/Carousel.vue";
import constants from "../lib/constants";
import axios from "axios";

export default {
  components: {
    Carousel,
  },
  data() {
    return {
      constants,
      user: {},
      lastPost: [],
      lastPostFields: [
        { key: "postNo", label: "의뢰번호" },
        { key: "title", label: "글제목" },
        { key: "option1", label: "VS" },
        { key: "option2", label: "VS" },
        { key: "postDate", label: "작성일" },
        { key: "userNickname", label: "의뢰자" },
        { key: "to", label: "바로가기" },
      ],
      lastVideo: [],
    };
  }, // data end
  created() {
    axios
      .get(process.env.VUE_APP_API_URL + "/post/search", {
        params: {
          searchby: "all",
          searchText: "",
          orderby: "postdate",
          limit: 5,
          offset: 0,
          tabOption: "NOW()>deadline",
        },
      })
      .then(({ data }) => {
        this.lastPost = data.object;
      });
    axios
      .get(process.env.VUE_APP_API_URL + "/video/search", {
        params: {
          limit: 3,
          offset: 0,
          option: " isPrivate=0 ",
          orderBy: "postDate",
        },
      })
      .then(({ data }) => {
        this.lastVideo = data.object;
      });
  }, //created end
  methods: {
    postDT(item) {
      let date = new Date(item);
      return date.toLocaleDateString();
    },
    noimage(event) {
      event.target.src = process.env.VUE_APP_IMGUP_URL + "/images/noimage.png";
    },
    uImgURL(item) {
      return process.env.VUE_APP_IMGUP_URL + item.userImage;
    },
    thumbURL(item) {
      return process.env.VUE_APP_IMGUP_URL + "/videos/" + item.thumbnail;
    },
    toResult(videoNo) {
      this.$router.push("/vdetail/" + videoNo);
    },
  }, //methods end
};
</script>

<style scoped>
@font-face {
  font-family: "Tmon";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.part .box {
  border: #fcd000 3px ridge;
  opacity: 0.8;
  background: linear-gradient(
    180deg,
    rgba(14, 36, 56, 1) 0%,
    rgba(32, 17, 95, 1) 100%
  );
  margin: 1rem;
  box-shadow: 5px 5px 5px;
  color: #e3d19e;
}

.b-table {
  /* border: #fcd000 2px solid; */
  opacity: 0.8;
  background: linear-gradient(
    180deg,
    rgba(14, 36, 56, 1) 0%,
    rgba(32, 17, 95, 1) 100%
  );
}
#hhh:hover {
  opacity: 0.6;
}
</style>
