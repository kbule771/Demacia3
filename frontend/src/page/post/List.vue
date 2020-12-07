<template>
  <div class="container m-auto" id="body" style="font-family: Tmon">
    <b-row align-h="center" class="mt-4">
      <b-col cols="12">
        <div class="mt-4" style="display: center">
          <b-button
            pill
            size="lg"
            variant="dark"
            class="d-btn p-3 mt-4"
            href="/write"
          >
            <b-icon icon="archive-fill" />&nbsp;투표 의뢰
          </b-button>
        </div>
      </b-col>
      <b-col cols="12" class="mb-4">
        <b-row align-h="between">
          <b-col cols="12" md="5">
            <b-input-group class="shadow1">
              <template v-slot:prepend style="width: 100px">
                <b-form-select
                  v-model="searchby"
                  :options="searchOptions"
                ></b-form-select>
              </template>
              <b-form-input
                v-model="searchText"
                placeholder="검색"
                @keyup.enter="search"
              ></b-form-input>
              <b-input-group-append>
                <b-button variant="dark" @click="search">
                  <b-icon-search />
                </b-button>
              </b-input-group-append>
            </b-input-group>
          </b-col>
          <!-- 정렬 드롭다운 -->
          <b-col cols="12" md="3">
            <b-form-select
              v-model="orderby"
              :options="orderOptions"
              class="shadow1"
            ></b-form-select>
          </b-col>
        </b-row>
      </b-col>
      <b-col cols="12" class="mb-4">
        <b-tabs
          class="p-0"
          card
          v-model="tabIndex"
          noody
          style="font-size: 2.5vh"
          fill
        >
          <b-tab title="전체" />
          <b-tab title="진행중" />
          <b-tab title="마감" />
        </b-tabs>
      </b-col>
      <!-- 리스트 시작 -->
      <b-col cols="12" v-for="(item, $index) in list" :key="$index" class="p-3">
        <b-row class="overflow-hidden box text-left mb-3 p-2">
          <b-col cols="12" md="4" class="p-0">
            <b-img
              id="sizepadding"
              :src="thumbURL(item)"
              :alt="item.postNo + '번째영상썸네일'"
              rounded
              style="width: 100%; max-height: 200px"
              @error="noimage"
            ></b-img>
          </b-col>
          <b-col cols="12" md="8">
            <b-row align-h="between">
              <b-col cols="12" md="4" class="d-inline-block pb-0 pt-0">
                <b-avatar
                  variant="secondary"
                  :src="uImgURL(item)"
                  class="ml-3 mr-3"
                ></b-avatar>
                <strong>{{ item.userNickname }}</strong>
              </b-col>
              <b-col cols="12" md="8" class="text-right pb-0 pt-0">
                <p class="m-0" style="font-size: 2.5vh">
                  <b-icon-hand-thumbs-up />
                  {{ item.totalLikes }}
                  <strong class="m-4" />
                  <b-icon-caret-right-square-fill />
                  {{ item.view }}
                  <strong class="m-4" />
                  <strong
                    style="color: red; font-weight: bold; font-family: digital"
                  >
                    {{ item.mileage }}
                  </strong>
                  <strong style="font-family: digital; font-weight: bold">
                    SOL
                  </strong>
                </p>
              </b-col>
            </b-row>
            <img
              class="max-small"
              v-if="isDeadLine(item)"
              src="../../assets/img/votenow1.png"
              style="
                position: absolute;
                width: auto;
                height: auto;
                max-width: 60%;
                max-height: 60%;
                margin-left: 20%;
                opacity: 0.5;
              "
            />
            <img
              class="max-small"
              v-else
              src="../../assets/img/closed.png"
              style="
                position: absolute;
                width: auto;
                height: auto;
                transform: rotate(-3deg);
                max-width: 70%;
                max-height: 90%;
                margin-left: 20%;
                margin-bottom: 4%;
                margin-top: -5%;
                opacity: 0.5;
              "
            />
            <b-row class="pb-0 pt-0">
              <b-col cols="12" md="8">
                <h3
                  style="
                    display: -webkit-box;
                    font-weight: bold;
                    overflow: hidden;
                    width: 100%;
                    text-overflow: ellipsis;
                    -webkit-line-clamp: 1;
                    -webkit-box-orient: vertical;
                    word-wrap: break-word;
                    line-height: 2em;
                    height: 2em;
                  "
                >
                  {{ item.title }}
                </h3>
              </b-col>

              <b-col cols="12" md="4" class="text-right">
                <h4 style="font-weight: bold; line-height: 2em">
                  {{ item.option1 }}
                  <strong style="color: red"> VS </strong>
                  {{ item.option2 }}
                </h4>
              </b-col>
            </b-row>

            <b-row align-h="between">
              <b-col> 의뢰번호 No. {{ item.postNo }} </b-col>

              <b-col class="text-right">
                <div
                  v-if="isDeadLine(item)"
                  class="btn btn-primary"
                  style="
                    width: 100%;
                    max-width: 200px;
                    height: 40px;
                    cursor: Pointer;
                  "
                  no-gutters
                  @click="toDetail(item.postNo)"
                >
                  투표하러가기
                </div>
                <div
                  v-else
                  class="btn btn-danger"
                  style="
                    width: 100%;
                    max-width: 200px;
                    height: 40px;
                    cursor: Pointer;
                  "
                  no-gutters
                  @click="toResult(item.postNo)"
                >
                  결과보러가기
                </div>
              </b-col>
            </b-row>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
  </div>
</template>
<script>
import axios from "axios";

const limit = 3;

export default {
  data() {
    return {
      offset: 0,
      list: [],
      main: [],
      isSearch: false,
      searchby: "title",
      searchOptions: [
        { value: "title", text: "제목" },
        { value: "userNickname", text: "닉네임" },
        { value: "option", text: "투표대상" },
      ],
      searchText: "",
      orderby: "postdate",
      orderOptions: [
        { value: "postdate", text: "최신순" },
        { value: "view", text: "조회수순" },
        { value: "mileage", text: "SOL순" },
      ],
      state: "",
      tabIndex: 0,
      tabOption: "1=1",
    };
  },

  created() {
    window.scrollTo(0, 0);
    this.dlTime = "00:00:00";
    axios
      .get(process.env.VUE_APP_API_URL + "/post/search", {
        params: {
          searchby: "all",
          searchText: "",
          orderby: "postdate",
          limit: limit,
          offset: this.offset,
          tabOption: this.tabOption,
        },
      })
      .then(({ data }) => {
        if (data.object) {
          this.main.push(...data.object);
        }
      });
  },

  computed: {},

  methods: {
    infiniteHandler($state) {
      this.state = $state;
      axios
        .get(process.env.VUE_APP_API_URL + "/post/search", {
          params: {
            searchby: this.isSearch ? this.searchby : "all",
            searchText: this.searchText,
            orderby: this.orderby,
            limit: limit,
            offset: this.offset,
            tabOption: this.tabOption,
          },
        })
        .then(({ data }) => {
          if (data.object) {
            this.offset += limit;
            this.list.push(...data.object);
            $state.loaded();
          } else {
            $state.complete();
          }
        });
    },
    search() {
      if (!this.searchText) window.location.reload();
      this.list = [];
      this.offset = 0;
      this.isSearch = true;
      this.state.reset();
      this.state.loaded();
    },
    noimage(event) {
      event.target.src = process.env.VUE_APP_IMGUP_URL + "/images/noimage.png";
    },
    uImgURL(item) {
      return process.env.VUE_APP_IMGUP_URL + item.userImage;
    },
    thumbURL(item) {
      return process.env.VUE_APP_IMGUP_URL + item.thumbnail;
    },

    toDetail(postNo) {
      // if (sessionStorage.getItem("user") == null) {
      //   this.$router.push("/login");
      // } else {
      this.$router.push("/detail/" + postNo);
      // }
    },

    toResult(postNo) {
      this.$router.push("/result/" + postNo);
    },

    isDeadLine(item) {
      let today = new Date();
      let dd = new Date(item.deadLine);

      if (today.getTime() > dd.getTime()) {
        if (item.isFinished == 0) {
          axios.get(process.env.VUE_APP_API_URL + "/update/mileage/", {
            params: {
              postNo: item.postNo,
            },
          });
        }
        return false;
      } else {
        return true;
      }
    },
  }, //end of method
  watch: {
    orderby(option) {
      this.list = [];
      this.offset = 0;
      this.isSearch = true;
      this.state.reset();
      this.state.loaded();
      return option;
    },
    tabIndex(i) {
      switch (i) {
        case 0:
          this.tabOption = "1=1";
          break;
        case 1:
          this.tabOption = "NOW()<deadline";
          break;
        case 2:
          this.tabOption = "NOW()>deadline";
          break;
      }
      this.list = [];
      this.offset = 0;
      this.isSearch = true;
      this.state.reset();
      this.state.loaded();
    },
  }, // end watch
};
</script>

<style scoped>
.shadow1 {
  box-shadow: 5px 5px 5px;
}
.shadow2 {
  box-shadow: 7px 7px 7px;
}
.carousel-caption {
  top: 0;
  bottom: auto;
}
@media screen and (max-width: 580px) {
  #hide1 {
    display: none;
  }
}
@media screen and (min-width: 1400px) {
  #sizepadding {
    padding: 0 !important;
  }
}
.blue-color {
  background-color: aqua;
}

.card-header {
  border-bottom: 3px solid black;
}
.nav-link.active {
  border-left: 3px solid;
  border-right: 3px solid;
  border-top: 3px solid;

  border-bottom: 3px solid;
  margin-bottom: -3px;
  border-radius: 10px 10px 3px 3px;
}
.nav-link {
  color: black;
  font-weight: bold;
}
.nav-tabs .nav-item.show .nav-link,
.nav-tabs .nav-link.active {
  color: white;
  border-top-color: black;
  border-left-color: black;
  border-right-color: black;
  border-bottom-color: black;
  background-color: black;
  font-weight: bold;
}
.tab-pane {
  padding: 0;
}
.d-btn {
  color: white;
  background: black;
  border: 5px ridge #8ab7f7;
}
.d-btn:hover {
  background: #8ab7f7;
}
.box {
  border-style: ridge;
  border: #fcd000 4px ridge;
  opacity: 0.8;
  background: linear-gradient(
    180deg,
    rgba(14, 36, 56, 1) 0%,
    rgba(32, 17, 95, 1) 100%
  );
  box-shadow: 5px 5px 5px;
  color: #e3d19e;
}
</style>
