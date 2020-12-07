<template>
  <div class="container m-auto" id="body" style="font-family: Tmon">
    <b-row align-h="center" class="mt-4">
      <b-col cols="12">
        <div class="mt-4" style="display: center">
          <b-button
            pill
            size="lg"
            variant="dark"
            class="d-btn1 p-3 mt-4"
            href="/vwrite"
          >
            <b-icon icon="camera-video-fill" />&nbsp;영상 분석 요청
          </b-button>
        </div>
      </b-col>
      <b-col cols="12" class="text-right">
        <b-form-select
          v-model="orderby"
          style="width: 300px"
          :options="orderOptions"
          class="shadow1"
        />
        <b-form-checkbox
          id="checkbox-private"
          v-model="isSearch"
          name="checkbox-private"
          :value="true"
          :unchecked-value="false"
          size="lg"
          class="m-2"
          style="color: white"
        >
          내 영상만 보기
        </b-form-checkbox>
      </b-col>
    </b-row>
    <b-row align-h="center">
      <b-col cols="12" md="4" v-for="(item, $index) in list" :key="$index">
        <!-- 리스트 시작 -->
        <b-row
          id="hhh"
          class="box text-left shadow1 m-2"
          style="margin-top: 10px; margin-bottom: 5%; cursor: Pointer"
          align-h="between"
          @click="toResult(item.videoPostNo)"
        >
          <b-col cols="12" class="m-0 p-2">
            <b-img
              id="sizepadding"
              :src="thumbURL(item)"
              :alt="item.videoNo + '번째영상썸네일'"
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
            >영상번호 No. {{ item.videoPostNo }}</b-col
          >
        </b-row>
      </b-col>
    </b-row>
    <infinite-loading @infinite="infiniteHandler"></infinite-loading>
  </div>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      userNo: "",
      offset: 0,
      limit: 3,
      list: [],
      isSearch: false,
      orderby: "postdate",
      orderOptions: [
        { value: "postdate", text: "최신순" },
        { value: "!postdate", text: "오래된순" },
        { value: "view", text: "조회순" },
        { value: "totalLike", text: "추천순" },
      ],
      state: "",
    };
  },

  created() {
    window.scrollTo(0, 0);
    this.userNo = JSON.parse(sessionStorage.getItem("user"))
      ? JSON.parse(sessionStorage.getItem("user")).userNo
      : "";
  },

  computed: {},

  methods: {
    infiniteHandler($state) {
      this.state = $state;
      let op = !this.userNo
        ? " isPrivate=0 "
        : this.isSearch
        ? " v.userNo = " + this.userNo
        : " isPrivate=0 OR v.userNo = " + this.userNo;
      axios
        .get(process.env.VUE_APP_API_URL + "/video/search", {
          params: {
            limit: this.limit,
            offset: this.offset,
            option: op,
            orderBy: this.orderby,
          },
        })
        .then(({ data }) => {
          if (data.object.length) {
            this.offset += this.limit;
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
      return process.env.VUE_APP_IMGUP_URL + "/videos/" + item.thumbnail;
    },

    toResult(videoNo) {
      this.$router.push("/vdetail/" + videoNo);
    },
  }, //end of method
  watch: {
    orderby(option) {
      this.list = [];
      this.offset = 0;
      this.state.reset();
      this.state.loaded();
      return option;
    },
    isSearch(o) {
      this.list = [];
      this.offset = 0;
      this.state.reset();
      this.state.loaded();
      return o;
    },
  }, // end watch
};
</script>

<style scoped>
#hhh:hover {
  opacity: 0.6;
}
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
.d-btn1 {
  color: white;
  background: black;
  border: 5px ridge #8ab7f7;
}
.d-btn1:hover {
  background: #8ab7f7;
}
</style>
