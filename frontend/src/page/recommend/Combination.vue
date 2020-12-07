<template>
  <b-container
    class="bv-example-row bv-example-row-flex-cols"
    style="font-family: Tmon"
  >
    <div>
      <img src="../../assets/img/picktitle.png" alt="" class="mt-5 mb-3" />
    </div>
    <div><img src="../../assets/img/combititle.png" alt="" class="mb-5" /></div>
    <h2 style="color:#8ab7f7">
      최근 가장 인기있는 메타기반으로 최고의 승률을 자랑하는 조합을 추천드려요
    </h2>

    <!-- 2명 파티 -->
    <div class="box px-3 chambox mb-5 pb-0">
      <div class="mb-2">
        <div class="chamtitle pl-4 px-auto">
          <h5 style="color: black">나만의 2인 최고 조합 추천</h5>
        </div>
      </div>
      <b-row align-v="stretch" class="section mb-0">
        <b-col>
          <div class="mb-3">
            <img src="../../assets/img/1badge.png" alt="" class="rankimg" />
          </div>
          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  first_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ first_cham.korname }} | {{ first_cham.chamname }}</b-badge
                >
              </h5>
            </b-col>
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[0].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[0].korname }} |
                  {{ comb_info[0].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>

        <b-col class="article">
          <div class="mb-3">
            <img src="../../assets/img/2badge.png" alt="" class="rankimg" />
          </div>
          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  second_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ second_cham.korname }} |
                  {{ second_cham.chamname }}</b-badge
                >
              </h5>
            </b-col>
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[4].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[4].korname }} |
                  {{ comb_info[4].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>

        <b-col>
          <div class="mb-3">
            <img src="../../assets/img/3badge.png" alt="" class="rankimg" />
          </div>
          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  third_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ third_cham.korname }} | {{ third_cham.chamname }}</b-badge
                >
              </h5>
            </b-col>
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[8].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[8].korname }} |
                  {{ comb_info[8].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
    </div>

    <!-- 3명 파티 -->
    <div class="box px-3 chambox mb-5 pb-0">
      <div>
        <div class="chamtitle pl-4 px-auto">
          <h5 style="color: black">나만의 3인 최고 조합 추천</h5>
        </div>
      </div>
      <b-row align-v="stretch" class="section mb-0">
        <b-col>
          <div class="mb-3">
            <img src="../../assets/img/1badge.png" alt="" class="rankimg" />
          </div>
          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  first_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <div class="mb-3">
                <h5>
                  <b-badge pill variant="warning" class="pickname"
                    >{{ first_cham.korname }} |
                    {{ first_cham.chamname }}</b-badge
                  >
                </h5>
              </div>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[0].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <b-badge pill variant="warning" class="pickname"
                >{{ comb_info[0].korname }} |
                {{ comb_info[0].chamname }}</b-badge
              >
            </b-col>

            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[1].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[1].korname }} |
                  {{ comb_info[1].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>

        <b-col class="article">
          <div class="mb-3">
            <img src="../../assets/img/2badge.png" alt="" class="rankimg" />
          </div>
          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  second_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <div>
                <h5>
                  <b-badge pill variant="warning" class="pickname mb-3"
                    >{{ second_cham.korname }} |
                    {{ second_cham.chamname }}</b-badge
                  >
                </h5>
              </div>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[4].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[4].korname }} |
                  {{ comb_info[4].chamname }}</b-badge
                >
              </h5>
            </b-col>

            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[5].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[5].korname }} |
                  {{ comb_info[5].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>

        <b-col>
          <div class="mb-3">
            <img src="../../assets/img/3badge.png" alt="" class="rankimg" />
          </div>
          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  third_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <div class="mb-3">
                <h5>
                  <b-badge pill variant="warning" class="pickname"
                    >{{ third_cham.korname }} |
                    {{ third_cham.chamname }}</b-badge
                  >
                </h5>
              </div>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[8].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[8].korname }} |
                  {{ comb_info[8].chamname }}</b-badge
                >
              </h5>
            </b-col>

            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[9].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[9].korname }} |
                  {{ comb_info[9].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
    </div>

    <!-- 5명 파티 -->
    <div class="box px-3 chambox mb-5 pb-0">
      <div class="mb-5">
        <div class="chamtitle pl-4 px-auto">
          <h5 style="color:black;">
            나만의 5인 최고 조합 추천
          </h5>
        </div>
      </div>
      <b-row align-v="stretch" class="section mb-0">
        <b-col>
          <div class="mb-3">
            <img src="../../assets/img/1badge.png" alt="" class="rankimg" />
          </div>
          <b-row align-v="center" class="mb-3">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  first_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ first_cham.korname }} | {{ first_cham.chamname }}</b-badge
                >
              </h5>
            </b-col>
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[0].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[0].korname }} |
                  {{ comb_info[0].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[1].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />

              <div class="mb-3">
                <h5>
                  <b-badge pill variant="warning" class="pickname"
                    >{{ comb_info[1].korname }} |
                    {{ comb_info[1].chamname }}</b-badge
                  >
                </h5>
              </div>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[2].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[2].korname }} |
                  {{ comb_info[2].chamname }}</b-badge
                >
              </h5>
            </b-col>
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[3].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[3].korname }} |
                  {{ comb_info[3].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>

        <b-col class="article">
          <div class="mb-3">
            <img src="../../assets/img/2badge.png" alt="" class="rankimg" />
          </div>

          <b-row align-v="center" class="mb-3">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  second_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ second_cham.korname }} |
                  {{ second_cham.chamname }}</b-badge
                >
              </h5>
            </b-col>

            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[4].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[4].korname }} |
                  {{ comb_info[4].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[5].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <div class="mb-3">
                <h5>
                  <b-badge pill variant="warning" class="pickname"
                    >{{ comb_info[5].korname }} |
                    {{ comb_info[5].chamname }}</b-badge
                  >
                </h5>
              </div>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[6].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[6].korname }} |
                  {{ comb_info[6].chamname }}</b-badge
                >
              </h5>
            </b-col>
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[7].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[7].korname }} |
                  {{ comb_info[7].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>

        <b-col>
          <div class="mb-3">
            <img src="../../assets/img/3badge.png" alt="" class="rankimg" />
          </div>

          <b-row align-v="center" class="mb-3">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  third_cham.imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ third_cham.korname }} | {{ third_cham.chamname }}</b-badge
                >
              </h5>
            </b-col>
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[8].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[8].korname }} |
                  {{ comb_info[8].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[9].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <div class="mb-3">
                <h5>
                  <b-badge pill variant="warning" class="pickname"
                    >{{ comb_info[9].korname }} |
                    {{ comb_info[9].chamname }}</b-badge
                  >
                </h5>
              </div>
            </b-col>
          </b-row>

          <b-row align-v="center">
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[10].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[10].korname }} |
                  {{ comb_info[10].chamname }}</b-badge
                >
              </h5>
            </b-col>
            <b-col>
              <b-img
                style="border-style: ridge; border: #fcd000 3px ridge"
                width="100%"
                :src="
                  comb_info[11].imgsrc
                    .replace('@/assets/img', '')
                    .replace('_1.jpg', '.png')
                "
              />
              <h5>
                <b-badge pill variant="warning" class="pickname"
                  >{{ comb_info[11].korname }} |
                  {{ comb_info[11].chamname }}</b-badge
                >
              </h5>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<style scoped>
@import "../../assets/css/combination.css";
</style>

<script>
import axios from "axios";

export default {
  data() {
    return {
      user: {},
      first_cham: {},
      second_cham: {},
      third_cham: {},
      cham_dic: [],
      comb_info: [],
      //0-3 : champion1
      //4-7 : champion2
      //8-11: champion3
      champion: [],
    };
  },

  created() {
    if (JSON.parse(sessionStorage.getItem("user"))) {
      this.user = JSON.parse(sessionStorage.getItem("user"));
      console.log(this.user);
      axios.get(process.env.VUE_APP_DAPI_URL + "/champions/").then((res) => {
        this.champion = res.data;
        axios
          .get(
            process.env.VUE_APP_DAPI_URL + "/matches/" + this.user.userNo + "/"
          )
          .then((res) => {
            if (res.data.length == "0") {
              alert("전적기록을 먼저 가져와주세요!!");
              location.href = "/myprofile";
            } else {
              axios
                .get(
                  process.env.VUE_APP_DAPI_URL +
                    "/recommand/" +
                    this.user.userNo +
                    "/group"
                )
                .then((res) => {
                  this.first_cham = res.data.First_cham[0];
                  this.second_cham = res.data.Second_cham[0];
                  this.third_cham = res.data.Third_cham[0];
                  this.cham_dic = JSON.parse(this.first_cham.dictionary)
                    .concat(JSON.parse(this.second_cham.dictionary))
                    .concat(JSON.parse(this.third_cham.dictionary));
                  // let temp = this.first_cham.dictionary
                  //   .replace("[", "")
                  //   .replace("]", "");
                  // this.cham_dic = temp.split(",");
                  // temp = this.second_cham.dictionary
                  //   .replace("[", "")
                  //   .replace("]", "");
                  // let temp2 = temp.split(",");
                  // temp2.forEach((e) => {
                  //   this.cham_dic.push(e);
                  // });
                  // temp = this.third_cham.dictionary
                  //   .replace("[", "")
                  //   .replace("]", "");
                  // let temp3 = temp.split(",");
                  // temp3.forEach((e) => {
                  //   this.cham_dic.push(e.trim());
                  // });
                  // console.log(this.cham_dic);
                  this.cham_dic.forEach((e) => {
                    this.comb_info.push(this.champion.find((o) => o.id == e));
                    //this.comb_info.push(res.data.find(o => o.id == chamno));
                    // setTimeout(() => this.callchaminfo(e), 1000);
                  });
                  // console.log(this.comb_info);
                })
                .catch((err) => {
                  location.href =
                    "/error/조합 중 서버 오류가 발생했습니다. " + err;
                });
            }
          })
          .catch((err) => {
            location.href =
              "/error/조합 추천 중 서버 오류가 발생했습니다. " + err;
          });
      });
    } else {
      location.href = "/login";
    }
  },

  computed: {},

  methods: {},
};
</script>

<style scoped>
.box {
  opacity: 0.8;
  background: linear-gradient(
    180deg,
    rgba(14, 36, 56, 1) 0%,
    rgba(32, 17, 95, 1) 100%
  );
  border: #fcd000 3px ridge;
  box-shadow: 5px 5px 5px;
  color: #e3d19e;
}
.rankimg {
  width: 150px;
}
</style>
