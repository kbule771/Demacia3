<template>
  <div class="container" style="heigth: 100%; font-family: Tmon">
    <b-row align-h="end" class="mb-4">
      <b-col cols="12" md="4" class="p-4">
        <!-- 유저정보 박스 -->
        <div class="box mt-4">
          <h2 style="color: rgb(252, 208, 0)">MY PAGE</h2>
          <!-- 프로필 이미지 -->
          <div v-b-modal.modalProfileImage href="#">
            <b-avatar
              badge-variant="dark"
              badge-offset="-10px"
              variant="secondary"
              :src="imgURL"
              size="10rem"
              class="mb-3"
            >
              <template v-slot:badge>
                <b-icon icon="upload"></b-icon>
              </template>
            </b-avatar>
          </div>
          <b-row class="mt-3" style="height: 3rem">
            <b-col class="mr-2">
              <!-- 닉네임 -->
              <h2 v-show="nicknameCheck" style="color: #fcd000">
                {{ user.userNickname }}
              </h2>
              <!-- 닉네임 수정시 입력박스 -->
              <b-input-group v-show="!nicknameCheck">
                <b-form-input
                  type="text"
                  placeholder="닉네임 입력"
                  id="nickname"
                  v-model="nickname"
                  :state="nicknameValid"
                  aria-describedby="nicknameFeedback"
                />
                <b-input-group-append>
                  <b-button variant="light" @click="nicknameUpdate"
                    >수정</b-button
                  >
                </b-input-group-append>
                <b-form-invalid-feedback id="nicknameFeedback">{{
                  nicknameMsg
                }}</b-form-invalid-feedback>
              </b-input-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col style="font-size: 20px; color: #e3d19e">{{
              user.userEmail
            }}</b-col>
          </b-row>

          <b-row class="pr-4 pl-4">
            <b-col cols="7" class="text-white">
              <!-- 마일리지랭킹 조회 -->
              <h6 class="mil">
                마일리지 랭킹 :
                <span style="color: red">
                  {{ usermilrank }}
                </span>
                위
              </h6>
              <h6 class="mil">(상위 {{ usermilrankper }}%)</h6>
            </b-col>
            <b-col class="p-0">
              <!-- \닉네임, 비밀번호, 회원탈퇴 드랍다운 -->
              <b-dropdown
                text="더 보기"
                dropright
                variant="outline-light"
                class="d-btn m-2"
                style="z-index: auto"
              >
                <b-dropdown-item @click="nicknameCheck = !nicknameCheck"
                  >닉네임 수정</b-dropdown-item
                >
                <b-dropdown-item @click="showPasswordChkModal"
                  >비밀번호 변경</b-dropdown-item
                >
                <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item @click="deleteUser">회원 탈퇴</b-dropdown-item>
              </b-dropdown>
              <!-- 이미지 변경 모달 -->
              <b-modal
                id="modalProfileImage"
                size="sm"
                title="프로필 사진 변경"
                variant="dark"
                centered
                hide-footer
              >
                <b-list-group-item>
                  <b-list-group-item @click="imgUp" href="#" variant="primary"
                    >내 기기에서 파일 탐색</b-list-group-item
                  >
                  <b-list-group-item @click="imgDel" href="#" variant="primary"
                    >프로필 사진 삭제</b-list-group-item
                  >
                  <b-list-group-item
                    href="#"
                    variant="secondary-outline"
                    v-b-modal.imageRule
                    >사진 업로드 이용규칙</b-list-group-item
                  >
                  <b-modal
                    id="imageRule"
                    title="프로필 사진 업로드 이용규칙"
                    ok-only
                    centered
                  >
                    프로필 사진은 회원 여러분의 개성을 드러낼 수 있는
                    서비스입니다.
                    <br />다만 원활한 서비스 제공에 문제를 일으키는 프로필
                    사진은 규제될 수 있음을 알려드리오니, 업로드 시 유의하여
                    주시기 바랍니다. <br />[규제 대상]
                    <ol>
                      <li>음란, 선정성 사진</li>
                      <li>욕설 등 타인에게 불쾌감을 주는 이미지</li>
                      <li>홍보 목적으로 URL이나 연락처가 기재된 이미지</li>
                      <li>폭력 묘사, 신체훼손 등 혐오감을 주는 이미지</li>
                      <li>
                        명예훼손, 저작권 침해 등 타인의 권리를 침해하는 이미지
                      </li>
                      <li>그 외 법령 및 약관에 위배되는 사진 및 이미지</li>
                    </ol>
                    규제 대상으로 확인된 이미지는 발견 즉시 무통보 삭제되며,
                    정도와 빈도에 따라 서비스 이용이 제한 될 수 있습니다.
                    <br />이의 사항 발생 시 관리자에게 문의바랍니다.
                    <br />
                    <br />건전하고 멋진 프로필 사진 사용으로 여러분의 개성을
                    뽐내주세요. 감사합니다.
                  </b-modal>
                </b-list-group-item>
              </b-modal>
              <input
                type="file"
                class="form-control"
                placeholder="프로필 등록"
                id="profil"
                ref="uploadImageFile"
                @change="onFileSelected"
                accept="image/*"
                style="display: none"
              />
              <!-- 비밀번호 재입력 모달 -->
              <b-modal ref="passwordChkModal" centered title="비밀번호 재입력">
                <b-input-group>
                  <b-form-input
                    type="password"
                    class="form-control"
                    placeholder="기존 비밀번호를 입력해주세요"
                    id="passwordChk"
                    v-model="passwordChk"
                    :state="passwordChkValid"
                    aria-describedby="passwordChkFeedback"
                    @keyup.enter="passwordCheck"
                  />
                  <b-form-invalid-feedback id="passwordChkFeedback">{{
                    passwordChkMsg
                  }}</b-form-invalid-feedback>
                </b-input-group>
                <template v-slot:modal-footer>
                  <b-button size="sm" variant="light" @click="passwordCheck"
                    >확인</b-button
                  >
                </template>
              </b-modal>
              <!-- 비밀번호 변경 모달 -->
              <b-modal ref="passwordUpdateModal" centered title="비밀번호 변경">
                <div class="form-group">
                  <b-form-input
                    type="password"
                    placeholder="비밀번호 : 영어, 숫자 혼용 8자 이상"
                    id="password"
                    v-model="password"
                    :state="passwordValid"
                    aria-describedby="pwFeedback"
                  />
                  <b-form-invalid-feedback id="pwFeedback">{{
                    passwordMsg
                  }}</b-form-invalid-feedback>
                </div>
                <div class="form-group">
                  <b-form-input
                    type="password"
                    placeholder="비밀번호 확인"
                    id="passwordconfirm"
                    v-model="passwordConfirm"
                    :state="passwordConfirmValid"
                    aria-describedby="pwconFeedback"
                    @keyup.enter="passwordUpdate"
                  />
                  <b-form-invalid-feedback id="pwconFeedback">{{
                    passwordConfirmMsg
                  }}</b-form-invalid-feedback>
                </div>
                <template v-slot:modal-footer>
                  <b-button size="sm" variant="light" @click="passwordUpdate"
                    >비밀번호 변경</b-button
                  >
                </template>
              </b-modal>
            </b-col>
          </b-row>
        </div>
      </b-col>

      <!-- 마일리지 박스 -->
      <b-col cols="12" md="4" align-self="center">
        <div
          class="box shadow1 d-flex justify-content-center"
          style="
            width: 300px;
            height: 100px;
            margin: auto;
            top: 50%;
            font-family: digital;
          "
        >
          <img
            src="../../assets/img/gold.png"
            alt=""
            style="
              width: 50px;
              height: 50px;
              margin-top: 7%;
              margin-right: 10px;
            "
          />
          <p
            style="
              font-size: 3vh;
              margin-top: 10%;
              color: red;
              font-weight: bold;
              padding-right: 4px;
            "
          >
            {{ user.userMileage }}
          </p>
          <p style="font-size: 3vh; margin-top: 10%" class="text-white">SOL</p>
        </div>
      </b-col>
    </b-row>

    <!-- 하단부 -->
    <b-row class="mt-4">
      <!-- 랭크 박스 -->
      <b-col cols="12" md="6" class="p-4">
        <!-- 솔로몬 랭크 -->
        <b-row no-gutters class="box mb-4">
          <b-col cols="3" md="4">
            <img
              :src="solRankImg"
              width="100%"
              alt="게임랭크"
              class="rounded-0"
            />
          </b-col>
          <b-col cols="9" md="8" class="p-2" align-self="center">
            <p
              id="font1"
              style="font-size: 3vh; font-weight: bold"
              class="text-white"
            >
              SOLOMON RANK
            </p>
            <p
              id="font1"
              style="font-size: 2.5vh; font-weight: bold; color: #fcd000"
            >
              {{ solRank }}
            </p>
          </b-col>
        </b-row>
        <!-- 게임 랭-->
        <b-row no-gutters class="box">
          <b-col cols="3" md="4">
            <img
              :src="gameRankImg"
              width="100%"
              alt="게임랭크"
              class="rounded-0"
            />
          </b-col>
          <b-col cols="9" md="8" align-self="center">
            <div v-show="user.summonerName">
              <p style="font-size: 3vh; font-weight: bold" class="text-white">
                GAME RANK
              </p>
              <p style="font-size: 2.5vh; font-weight: bold; color: #fcd000">
                {{ gameRank }}
              </p>
              <b-col class="d-flex justify-content-center">
                <b-button
                  class="d-btn hoverbtn"
                  style="max-width: 200px"
                  block
                  @click="summonerReset"
                  >초기화</b-button
                >
              </b-col>
            </div>
            <div v-show="!user.summonerName">
              <p
                id="font1"
                style="font-size: 3vh; font-weight: bold"
                class="text-white"
              >
                GAME RANK
              </p>
              <p style="font-size: 2.5vh; font-weight: bold; color: #fcd000">
                소환사 인증이 필요합니다!
              </p>
              <b-col>
                <!-- 소환사 인증 폼 -->
                <b-input-group>
                  <b-form-input
                    type="text"
                    placeholder="소환사명 입력"
                    id="summoner"
                    v-model="summoner"
                    :state="summonerValid"
                    aria-describedby="summonerFeedback"
                    class="pb-2"
                    style="
                      border-style: ridge;
                      border: #fcd000 3px ridge;
                      background-color: #1e2328;
                      color: #cdbe91;
                    "
                  />
                  <b-input-group-append class="dark">
                    <b-button @click="summonerAuth" class="d-btn hoverbtn"
                      >인증</b-button
                    >
                  </b-input-group-append>
                  <b-form-invalid-feedback id="summonerFeedback">{{
                    summonerMsg
                  }}</b-form-invalid-feedback>
                </b-input-group>
              </b-col>
            </div>
          </b-col>
        </b-row>
      </b-col>
      <b-col cols="12" md="6" class="p-4">
        <div no-gutters class="box" style="height: 100%">
          <b-row align-h="end" class="m-3">
            <b-col cols="4">
              <!-- 솔로몬 활동 리스트 박스-->
              <h4 style="display: inline-block" class="text-white">
                솔로몬 활동
              </h4>
            </b-col>
            <b-col cols="4" class="p-3">
              <h6
                style="display: inline-block; float: right"
                class="text-white"
              >
                적중률 : {{ userhitrate }}%
              </h6>
            </b-col>
          </b-row>
          <ul class="solomonul text-white">
            <li v-for="(item, $index) in listsol" :key="$index">
              <div class="solomon">
                <div class="title">{{ item.postTitle }}</div>
                <div class="result">{{ item.voteResult }}</div>
                <div class="point1" v-if="item.voteResult == 'Win'">
                  +{{ item.point }}SOL
                </div>
                <div class="point2" v-else-if="item.voteResult == 'Proceeding'">
                  {{ item.point }}SOL
                </div>
                <div class="point3" v-else>-{{ item.point }}SOL</div>
                <div class="icon" v-if="item.voteResult == 'Proceeding'">
                  <b-icon
                    class="icon"
                    icon="arrow-right-circle"
                    font-scale="2"
                    style="cursor: pointer"
                    @click="toDetail(item.postNo)"
                  ></b-icon>
                </div>
                <div class="icon" v-else>
                  <b-icon
                    class="icon"
                    icon="arrow-right-circle"
                    font-scale="2"
                    style="cursor: pointer"
                    @click="toResult(item.postNo)"
                  ></b-icon>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </b-col>

      <!-- MBTI -->
      <b-col cols="12" md="6" class="p-4">
        <b-row no-gutters class="box p-3">
          <b-col cols="12">
            <h4 class="text-white">내 MBTI</h4>
          </b-col>
          <b-col cols="12" v-if="!user.mbti">
            아직 데마시아 전용 MBTI 측정을 하지 않았습니다.
            <br />
            <b-button
              size="lg"
              variant="secondary"
              href="/mbtisurvey"
              class="d-btn hoverbtn"
              >MBTI 설문하기</b-button
            >
          </b-col>
          <b-col cols="12" v-else>
            <h2 style="color: #fcd000">{{ user.mbti.toUpperCase() }}</h2>
            <br />
            <b-button size="lg" href="/mbti" class="mr-2 d-btn hoverbtn">
              챔피언 추천!
            </b-button>
            <b-button
              size="lg"
              variant="secondary"
              href="/mbtisurvey"
              class="ml-2 d-btn hoverbtn"
            >
              다시 설문하기
            </b-button>
          </b-col>
        </b-row>
      </b-col>

      <!-- 매치데이터 업데이트 -->
      <b-col cols="12" md="6" class="p-4">
        <b-row no-gutters class="box p-3" style="height: 100%">
          <b-col cols="12">
            <h4 style="display: inline-block" class="text-white">
              전적기록(MatchList)
            </h4>
          </b-col>
          <b-col cols="12">
            <br />
            <b-button
              size="lg"
              variant="primary"
              class="d-btn mr-2 mt-3"
              @click="callmatch()"
              v-if="!loading"
            >
              가져오기
            </b-button>
            <b-spinner v-else large />
            <!-- <b-button variant="primary" v-else style="text-align: center">
              <span class="sr-only">Loading...</span>
            </b-button> -->
          </b-col>
        </b-row>
      </b-col>

      <!-- 활동 목록 -->
      <b-col cols="12" class="p-4">
        <h2 class="p-4" style="color: #fcd000">투표의뢰목록</h2>
        <b-table
          hover
          :items="myPostList"
          :fields="myPostFields"
          class="box"
          table-variant="dark"
        >
          <template v-slot:cell(postDate)="data">{{
            postDT(data.value)
          }}</template>
          <template v-slot:cell(to)="data">
            <b-icon
              class="icon"
              icon="arrow-right-circle"
              font-scale="2"
              style="cursor: pointer"
              @click="toDetail(data.item.postNo)"
            ></b-icon>
          </template>
        </b-table>
      </b-col>
      <!-- 영상분석 -->
      <b-col cols="12" class="p-4">
        <h2 class="p-4" style="color: #fcd000">영상분석 의뢰목록</h2>
        <b-table
          hover
          :items="myVideoPostList"
          :fields="myVideoPostFields"
          class="box"
          table-variant="dark"
        >
          <template v-slot:cell(thumbnail)="data">
            {{ data.item.thumbnail.replace(".jpg", "") }}
          </template>
          <template v-slot:cell(postdate)="data">
            {{ postDT(data.item.postdate) }}
          </template>
          <template v-slot:cell(to)="data">
            <b-icon
              class="icon"
              icon="arrow-right-circle"
              font-scale="2"
              style="cursor: pointer"
              @click="toVideoDetail(data.item.videoPostNo)"
            ></b-icon>
          </template>
        </b-table>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import "../../assets/css/profile.css";
import axios from "axios";
import PV from "password-validator";

export default {
  data() {
    return {
      userNo: "",
      user: {},
      passwordSchema: "",
      pwUpdate: false,
      nickname: "",
      nicknameCheck: true,
      nicknameValid: null,
      nicknameMsg: "",
      passwordChk: "",
      passwordChkValid: null,
      passwordChkMsg: "",
      password: "",
      passwordValid: null,
      passwordMsg: "",
      passwordConfirm: "",
      passwordConfirmValid: null,
      passwordConfirmMsg: "",
      summoner: "",
      summonerValid: null,
      summonerMsg: "",
      myPostList: [],
      myPostFields: [
        { key: "postNo", label: "의뢰번호" },
        { key: "title", label: "글제목" },
        { key: "option1", label: "VS" },
        { key: "option2", label: "VS" },
        { key: "postDate", label: "작성일" },
        { key: "to", label: "바로가기" },
      ],
      myVideoPostList: [],
      myVideoPostFields: [
        { key: "videoPostNo", label: "의뢰번호" },
        { key: "thumbnail", label: "파일명" },
        { key: "videoPostNo", label: "의뢰번호" },
        { key: "postDate", label: "작성일" },
        { key: "to", label: "바로가기" },
      ],
      listsol_tmp: [],
      listsol: [],
      userhitrate: "",
      usermilrank: "",
      usermilrankper: "",
      loading: false,
    };
  },
  created() {
    window.scrollTo(0, 0);
    this.passwordSchema = new PV()
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
    this.userNo = sessionStorage.getItem("user")
      ? JSON.parse(sessionStorage.getItem("user")).userNo
      : false;
    if (!this.userNo && (alert("사용자정보가 없습니다. 로그인 하세요.") || 1)) {
      location.href = "/login";
    }
    axios
      .get(process.env.VUE_APP_API_URL + "/update/solrank", {
        params: {
          userNo: this.userNo,
        },
      })
      .then(() => {
        axios
          .get(process.env.VUE_APP_API_URL + "/account/user", {
            params: {
              userNo: this.userNo,
            },
          })
          .then(({ data }) => {
            sessionStorage.removeItem("user");
            sessionStorage.setItem("user", JSON.stringify(data.object));
            this.user = data.object;
            axios
              .get(process.env.VUE_APP_API_URL + "/post/search", {
                params: {
                  searchby: "userNickname",
                  searchText: this.user.userNickname,
                  orderby: "postdate",
                  limit: 5,
                  offset: 0,
                  tabOption: "1=1",
                },
              })
              .then(({ data }) => {
                this.myPostList = data.object;
                this.solomonhire();
              });
            axios
              .get(process.env.VUE_APP_API_URL + "/video/search", {
                params: {
                  limit: 5,
                  offset: 0,
                  option: " v.userNo = " + this.userNo,
                  orderBy: "postDate",
                },
              })
              .then(({ data }) => {
                this.myVideoPostList = data.object;
                console.log(this.myVideoPostList);
              });
          });
      })
      .catch(() => {
        location.href = "/error/회원정보 조회 중 오류가 발생했습니다.";
      });
  },
  methods: {
    postDT(item) {
      let date = new Date(item);
      return date.toLocaleDateString() + " " + date.toLocaleTimeString();
    },
    solomon() {
      //솔로몬활동
      axios
        .get(process.env.VUE_APP_API_URL + "/account/uservote", {
          params: {
            userNo: this.userNo,
          },
        })
        .then(({ data }) => {
          this.listsol_tmp = data.object;
          this.listsol = this.listsol_tmp.reverse();
          this.listsol.splice("5");
          for (var i in this.listsol) {
            if (this.listsol[i].postTitle.length > 8) {
              this.listsol[i].postTitle =
                this.listsol[i].postTitle.substr(0, 8) + "...";
            }
          }
          this.milerankper();
        })
        .catch(() => {
          location.href = "/error/솔로몬 활동 에러";
        });
    },

    solomonhire() {
      //솔로몬 활동 적중률
      axios
        .get(process.env.VUE_APP_API_URL + "/account/userhitrate", {
          params: {
            userNo: this.userNo,
          },
        })
        .then(({ data }) => {
          var tmp = data.object;
          console.log(tmp);
          if (tmp == null) {
            tmp = 0;
          }
          this.userhitrate = tmp.toFixed(2) * 100;
          this.solomon();
        })
        .catch(() => {
          location.href = "/error/솔로몬 활동 적중률 에러";
        });
    },

    milerank() {
      //유저 마일리지 랭킹
      axios
        .get(process.env.VUE_APP_API_URL + "/user/mileagerank", {
          params: {
            userNo: this.userNo,
          },
        })
        .then(({ data }) => {
          this.usermilrank = data.object;
        })
        .catch(() => {
          location.href = "/error/유저 마일리지 랭킹 에러";
        });
    },

    milerankper() {
      //유저 마일리지 랭킹 퍼센트
      axios
        .get(process.env.VUE_APP_API_URL + "/user/mileagerankper", {
          params: {
            userNo: this.userNo,
          },
        })
        .then(({ data }) => {
          var tmp = data.object;
          this.usermilrankper = (tmp * 100).toFixed(2);
          this.milerank();
        })
        .catch(() => {
          location.href = "/error/유저 마일리지 랭킹 퍼센트";
        });
    },

    editmove() {
      location.href = "/mypage";
    },
    summonerAuth() {
      let err = false;
      this.summonerValid = !(
        !this.summoner &&
        ((this.summonerMsg = "소환사명을 입력해주세요."), (err = true))
      );
      if (!err) {
        axios
          .get(process.env.VUE_APP_API_URL + "/rank", {
            params: {
              summonerName: this.summoner,
              userNo: this.user.userNo,
            },
          })
          .then(() => {
            location.reload();
          });
      }
    },
    summonerReset() {
      axios
        .delete(process.env.VUE_APP_API_URL + "/rank", {
          params: { userNo: this.user.userNo },
        })
        .then(() => {
          location.reload();
        });
      this.user.summonerName = this.user.userGameTierStr = this.user.userGameRankStr = null;
      this.dataUpdate();
    },
    nicknameUpdate() {
      let err = false;
      this.nicknameValid = !(
        !this.nickname &&
        ((this.nicknameMsg = "닉네임을 입력해주세요."), (err = true))
      );
      if (!err) {
        axios
          .get(process.env.VUE_APP_API_URL + "/account/checkNickname", {
            params: {
              userNickname: this.nickname,
            },
          })
          .then((response) => {
            if (response.data.status) {
              this.nicknameCheck = false;
              this.nicknameValid = null;
              this.user.userNickname = this.nickname;
              this.dataUpdate();
            } else {
              this.nicknameMsg = "이미 등록된 닉네임입니다.";
              this.nicknameValid = false;
            }
          });
      }
    },
    showPasswordChkModal() {
      if (this.user.providerName) {
        this.errToast("소셜 회원입니다.", "비밀번호를 변경할 수 없습니다.");
      } else {
        this.$refs["passwordChkModal"].show();
      }
    },
    passwordCheck() {
      this.user.userPw == this.passwordChk
        ? (this.$refs["passwordChkModal"].hide(),
          this.$refs["passwordUpdateModal"].show())
        : ((this.passwordChkValid = false),
          (this.passwordChkMsg = "비밀번호가 일치하지 않습니다."));
    },
    passwordUpdate() {
      let err = false;
      this.passwordValid = !(
        (!this.password &&
          ((this.passwordMsg = "비밀번호를 입력해주세요."), (err = true))) ||
        (this.password &&
          !this.passwordSchema.validate(this.password) &&
          ((this.passwordMsg = "영어, 숫자 혼용 8자 이상으로 입력해주세요."),
          (err = true)))
      );
      this.passwordConfirmValid = !(
        (!this.passwordConfirm &&
          ((this.passwordConfirmMsg = "비밀번호를 확인해주세요."),
          (err = true))) ||
        (this.passwordConfirm &&
          this.password != this.passwordConfirm &&
          ((this.passwordConfirmMsg = "비밀번호와 비밀번호 확인이 다릅니다."),
          (err = true)))
      );
      if (!err) {
        this.user.userPw = this.password;
        this.dataUpdate();
      }
    },
    imgUp() {
      document.querySelector("#profil").click();
    },
    imgDel() {
      this.user.userImage = null;
      this.dataUpdate();
    },
    onFileSelected() {
      this.uploadImageFile = this.$refs.uploadImageFile.files[0];
      const fd = new FormData();
      fd.append("upLoadImage", this.uploadImageFile, this.user.userNo + ".jpg");
      axios
        .post(process.env.VUE_APP_IMGUP_URL + "/upload", fd, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          console.log("succes");
          this.user.userImage = "/images/" + this.user.userNo + ".jpg";
          this.dataUpdate();
        })
        .catch(() => {
          console.log("fail");
        });
    },
    dataUpdate() {
      axios
        .put(process.env.VUE_APP_API_URL + "/account/", this.user)
        .then(() => {
          location.reload();
        })
        .catch(() => {
          console.log("fail");
        });
    },
    errToast(title, msg) {
      this.$bvToast.toast(msg, {
        title: title,
        toaster: "b-toaster-top-center",
        solid: true,
        appendToast: false,
        variant: "primary",
      });
    },
    deleteUser() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        axios
          .delete(process.env.VUE_APP_API_URL + "/account/", {
            params: {
              userNo: this.userNo,
            },
          })
          .then(() => {
            alert("탈퇴되었습니다. 이용해 주셔서 감사합니다.");
            let tk = JSON.parse(sessionStorage.getItem("user")).accesstoken;
            if (!tk) {
              sessionStorage.removeItem("user");
              location.href = "/";
            } else {
              axios
                .get(process.env.VUE_APP_API_URL + "/logout/oauth", {
                  params: {
                    accesstoken: tk,
                  },
                })
                .then(() => {
                  sessionStorage.removeItem("user");
                  location.href = "/";
                });
            }
          })
          .catch(() => {
            location.href = "/error/회원 탈퇴 중 문제가 발생했습니다.";
          });
      }
    },

    toVideoDetail(videoPostNo) {
      this.$router.push("/vdetail/" + videoPostNo);
    },
    toDetail(postNo) {
      this.$router.push("/detail/" + postNo);
    },

    toResult(postNo) {
      this.$router.push("/result/" + postNo);
    },

    callmatch() {
      this.loading = true;
      axios
        .post(process.env.VUE_APP_DAPI_URL + "/matches/update/", {
          userno: this.userNo,
        })
        .then((res) => {
          console.log(res);
          this.loading = false;
          if (res.status == 200) {
            alert("기록을 가져왔습니다!!");
          }
        })
        .catch((err) => {
          this.loading = false;
          location.href =
            "/error/전적기록을 불러오던 중 서버 오류가 발생했습니다. " + err;
        });
    },
  },

  computed: {
    imgURL() {
      return this.user.userImage
        ? process.env.VUE_APP_IMGUP_URL + this.user.userImage
        : "";
    },
    gongsaing() {
      return process.env.VUE_APP_IMGUP_URL + "/images/gongsaing.jpg";
    },
    gameRankImg() {
      return (
        process.env.VUE_APP_IMGUP_URL +
        "/images/emblems/" +
        (this.user.userGameTierStr
          ? "Emblem_" + this.user.userGameTierStr.toLowerCase() + ".png"
          : "default.png")
      );
    },
    gameRank() {
      return this.user.userGameTierStr
        ? this.user.userGameTierStr + " - " + this.user.userGameRankStr
        : "Unranked";
    },
    solRankImg() {
      return (
        process.env.VUE_APP_IMGUP_URL +
        "/images/emblems/" +
        (this.user.userSolTierStr
          ? "Emblem_" + this.user.userSolTierStr.toLowerCase() + ".png"
          : "default.png")
      );
    },
    solRank() {
      return this.user.userSolRankStr
        ? this.user.userSolTierStr + " - " + this.user.userSolRankStr
        : "Unranked";
    },
  },
};
</script>

<style scoped>
@media screen and (min-width: 1024px) {
  #pad1 {
    margin-left: 15%;
  }
}
@media screen and (min-width: 1650px) {
  #pad2 {
    margin-left: 5%;
  }
}

@media screen and (max-width: 430px) {
  #font1 {
    font-size: 15px !important;
  }
}
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
.d-btn {
  color: white !important;
  background: linear-gradient(180deg, #1b2831 0%, #12384b 100%);
  border: #95ede7 3px ridge !important;
}
.da {
  background-color: rgb(222, 226, 228);
}
.shadow1 {
  box-shadow: 5px 5px 5px;
}
</style>
