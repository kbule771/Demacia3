<template>
  <b-container class="bv-example-row bv-example-row-flex-cols">
    <b-card class="m-2 chambox" style="color: white">
      <b-row align-v="start">
        <b-col>
          <h2>
            <strong style="color: yellow">{{ user.userNickname }}</strong
            >님의 MBTI는
            <strong style="color: red">'{{ user.mbti.toUpperCase() }}'</strong>
            입니다.
          </h2>
        </b-col>
      </b-row>
      <h1 class="text-center"><br />{{ user.mbti.toUpperCase() }}</h1>
      <h2 class="text-center">
        {{ MBTI.find((o) => o.mbti == user.mbti.toUpperCase()).mbti_info }}
      </h2>
      <h3 class="text-center">
        {{ MBTI.find((o) => o.mbti == user.mbti.toUpperCase()).mbti_text }}
      </h3>
      <b-container class="p-4">
        <b-jumbotron class="m-4 p-4">
          <b-row align-v="start">
            <b-col cols="12">
              <h2>{{ user.mbti }}에 어울리는 챔피언</h2>
            </b-col>
            <b-col
              cols="12"
              md="3"
              v-for="(item, $index) in chams.mbti_chams"
              :key="$index"
              class="text-left mt-3"
              style="font-size: 20px"
            >
              <b-img
                class="chamimg"
                thumbnail
                width="100%"
                :src="
                  item.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              {{ item.korname }}
            </b-col>
          </b-row>
        </b-jumbotron>
      </b-container>

      <b-container class="p-4">
        <b-jumbotron class="m-4 p-4">
          <b-row align-v="start">
            <b-col cols="12">
              <h2>
                최상의 조합 :
                <strong style="color: #51b9d6">{{
                  chams.good_chams[0].mbti
                }}</strong>
              </h2>
            </b-col>
            <b-col
              cols="12"
              md="3"
              v-for="(item, $index) in chams.good_chams"
              :key="$index"
              class="text-left mt-3"
              style="font-size: 20px"
            >
              <b-img
                class="chamimg"
                thumbnail
                width="100%"
                :src="
                  item.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              {{ item.korname }}
            </b-col>
          </b-row>
        </b-jumbotron>
      </b-container>

      <b-container class="p-4">
        <b-jumbotron class="m-4 p-4">
          <b-row align-v="start">
            <b-col cols="12">
              <h2>
                최악의 조합 :
                <strong style="color: #51b9d6">{{
                  chams.bad_chams[0].mbti
                }}</strong>
              </h2>
            </b-col>
            <b-col
              cols="12"
              md="3"
              v-for="(item, $index) in chams.bad_chams"
              :key="$index"
              class="text-left mt-3"
              style="font-size: 20px"
            >
              <b-img
                class="chamimg"
                thumbnail
                width="100%"
                :src="
                  item.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              {{ item.korname }}
            </b-col>
          </b-row>
        </b-jumbotron>
      </b-container>
    </b-card>
  </b-container>
</template>

<script>
import "../../assets/css/mbti.css";
import axios from "axios";

export default {
  data() {
    return {
      user: {},
      chams: {},
      MBTI: [
        {
          mbti: "ISTJ",
          mbti_info: "세상의 소금형",
          mbti_text: "한번 시작한 일은 끝까지 해내는 사람입니다.",
        },
        {
          mbti: "ISFJ",
          mbti_info: "임금뒷편의 권력형",
          mbti_text: "성실하고 온화하며 협조를 잘하는 사람입니다.",
        },
        {
          mbti: "INFJ",
          mbti_info: "예언자형",
          mbti_text: "사람과 관련된 뛰어난 통찰력을 가진 사람입니다.",
        },
        {
          mbti: "INTJ",
          mbti_info: "과학자형",
          mbti_text: "전체적인 부분을 조합하여 비전을 제시하는 사람입니다.",
        },
        {
          mbti: "ISTP",
          mbti_info: "백과사전형",
          mbti_text: "논리적이고 뛰어난 상황 적응력을 가진 사람입니다.",
        },
        {
          mbti: "ISFP",
          mbti_info: "성인군자형",
          mbti_text: "따뜻한 감성을 가지고 있는 겸손한 사람입니다.",
        },
        {
          mbti: "INFP",
          mbti_info: "잔다르크형",
          mbti_text: "이상적인 세상을 만들어 가는 사람입니다.",
        },
        {
          mbti: "INTP",
          mbti_info: "아이디어 뱅크형",
          mbti_text: "비평적인 관심을 가지고 있는 뛰어난 전략가입니다.",
        },
        {
          mbti: "ESTP",
          mbti_info: "수완좋은 활동가형",
          mbti_text: "친구, 운동, 음식 등 다양함을 선호하는 사람입니다.",
        },
        {
          mbti: "ESFP",
          mbti_info: "사교적인 유형",
          mbti_text: "분위기를 고조시키는 우호적인 사람입니다.",
        },
        {
          mbti: "ENFP",
          mbti_info: "스파크형",
          mbti_text: "열정적으로 새로운 관계를 만드는 사람입니다.",
        },
        {
          mbti: "ENTP",
          mbti_info: "발명가형",
          mbti_text: "풍부한 상상력을 가지고 새로운 것에 도전하는 사람입니다.",
        },
        {
          mbti: "ESTJ",
          mbti_info: "사업가형",
          mbti_text: "사무적, 실용적, 현실도모적인 일을 많이 하는 사람입니다.",
        },
        {
          mbti: "ESFJ",
          mbti_info: "친선도모형",
          mbti_text: "친절과 현실감을 바탕으로 타인에게 봉사하는 사람입니다.",
        },
        {
          mbti: "ENFJ",
          mbti_info: "언번능숙형",
          mbti_text: "타인의 성장을 도모하고 협동하는 사람입니다.",
        },
        {
          mbti: "ENTJ",
          mbti_info: "지도자형",
          mbti_text: "비전을 가지고 사람들을 활력적으로 이끌어가는 사람입니다.",
        },
      ],
    };
  },

  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    if (!this.user) location.href = "/login";
    else if (!this.user.mbti) location.href = "/mbtisurvey";
    axios
      .get(
        process.env.VUE_APP_DAPI_URL +
          "/mbti/" +
          this.user.mbti +
          // 중요
          "/"
        // 중요
      )
      .then((res) => {
        this.chams = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {},
  methods: {},
};
</script>

<style scoped>
.chambox {
  border-style: ridge;
  border: #e3d19e 1px ridge;
  height: 80%;
  opacity: 0.8;
  background: linear-gradient(
    180deg,
    rgba(14, 36, 56, 1) 0%,
    rgba(32, 17, 95, 1) 100%
  );
}
.chamimg {
  border-style: ridge;
  border: #fcd000 5px ridge;
  border-radius: 50%;
}
.jumbotron {
  background-color: #111822;
  border: 3px ridge rgb(252, 208, 0);
}
</style>
