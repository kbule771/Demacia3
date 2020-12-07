<template>
  <div style="height: 100%; font-family: Tmon">
    <b-row class="justify-content-center">
      <b-col cols="10" md="6" lg="4" style="margin-top: 5%">
        <div class="box">
          <h2 style="font-weight: bold">영상 등록</h2>
          <div class="row">
            <div class="col-md-12 text-center" style="height: 20px">
              <p v-show="isHover">{{ popoverStr[isVideoUploaded] }}</p>
            </div>
            <div class="col-md-12 text-center">
              <b-icon
                icon="caret-right-square-fill"
                font-scale="13"
                type="button"
                @click="openUploadWindows"
                @mouseover="isHover = true"
                @mouseleave="isHover = false"
                v-if="isVideoUploaded == 0"
              />
              <b-icon
                icon="gear-fill"
                animation="spin"
                font-scale="13"
                type="button"
                @click="cancelUpload"
                @mouseover="isHover = true"
                @mouseleave="isHover = false"
                v-else-if="isVideoUploaded == 1"
              />
              <b-icon
                icon="file-check"
                font-scale="13"
                type="button"
                @click="dataCheck"
                @mouseover="isHover = true"
                @mouseleave="isHover = false"
                v-else-if="isVideoUploaded == 2"
              />
              <b-icon
                icon="x-circle-fill"
                font-scale="13"
                type="button"
                v-else-if="isVideoUploaded == 3"
                @mouseover="isHover = true"
                @mouseleave="isHover = false"
                @click="reload"
              />
              <h2 class="m-4">{{ uploadMSG }}</h2>
              <!---->
              <input
                type="file"
                id="videoURL"
                ref="uploadvideoURL"
                @change="onSaveVideo"
                accept="video/*"
                aria-describedby="addon-right addon-left"
                placeholder="영상 업로드"
                hidden
              />
            </div>
            <div class="col-md-12 p-0">
              <b-form-checkbox
                id="checkbox-private"
                v-model="isPrivate"
                name="checkbox-private"
                :value="1"
                :unchecked-value="0"
                >나만 보기</b-form-checkbox
              >
            </div>
            <div class="col-md-12">
              파일 업로드 시 녹화한 파일명 그대로
              <br />
              (00-00_KR-000000000_00.mp4) 올려주세요!<br /><br />
              내 Sol 포인트 :
              <Strong>{{ userMileage }}</Strong>
              <p style="color: red">※ 50 Sol차감</p>
            </div>
          </div>
          <!---->
        </div>
      </b-col>
    </b-row>
  </div>
</template>


<script>
import axios from "axios";

export default {
  name: "Write",

  data: function () {
    return {
      no: JSON.parse(sessionStorage.getItem("user")).userNo,
      isVideoUploaded: 0,
      popoverStr: ["동영상 업로드", "업로드 취소", "영상 게시", "재시도"],
      isHover: false,
      mileage: 50,
      thumbnail: "0",
      data: "0",
      video: "0",
      userMileage: "",
      uploadMSG: "　",
      isPrivate: 0,
    };
  }, //data
  created() {
    window.scrollTo(0, 0);
    this.getUserMileage();
  },
  methods: {
    reload() {
      location.reload();
    },
    cancelUpload() {
      this.isVideoUploaded = 0;
    },
    openUploadWindows() {
      document.getElementById("videoURL").click();
      this.isVideoUploaded = 1;
    },
    getUserMileage() {
      axios
        .get(process.env.VUE_APP_API_URL + "/user/mileage", {
          params: {
            userNo: this.no,
          },
        })
        .then((res) => {
          this.userMileage = res.data.object;
        });
    },
    onSaveVideo() {
      let fileURL = this.$refs.uploadvideoURL.files[0];
      this.video = this.$refs.uploadvideoURL.files[0].name;
      this.thumbnail = this.video.replace(".mp4", ".jpg");
      console.log(this.video);
      let fdv = new FormData();
      fdv.append("upLoadVideo2", fileURL, this.video);
      this.uploadMSG = "업로드 중...";
      axios
        .post(process.env.VUE_APP_IMGUP_URL + "/uploadvideo2", fdv, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          console.log("video upload success");
          this.uploadMSG = "영상 분석중...";
          // DATA APIAPI
          axios
            .post(process.env.VUE_APP_DAPI_URL + "/videoposts/update/", {
              video: this.video,
            })
            .then((res) => {
              console.log(res.data);
              this.data = JSON.stringify(res.data[0]);
              console.log(this.data);
              this.uploadMSG = "영상 업로드 및 분석 성공";
              this.isVideoUploaded = 2;
            })
            .catch((e) => {
              console.log("data fail : " + e);
              this.uploadMSG = "분석 실패! 파일 형식을 지켜주세요.";
              this.isVideoUploaded = 3;
            });
        })
        .catch((e) => {
          console.log("video upload fail : " + e);
          this.uploadMSG = "업로드 실패";
          this.isVideoUploaded = 3;
        });
    },

    dataCheck() {
      let err = false;
      let msg = "";
      this.userMileage - this.mileage < 0 &&
        ((msg = "마일리지가 부족합니다."), (err = true));
      if (err) alert(msg);
      else this.dataSend();
    }, //dataCheck

    dataSend() {
      axios
        .post(process.env.VUE_APP_API_URL + "/video/", {
          userNo: this.no,
          video: this.video,
          thumbnail: this.thumbnail,
          data: this.data,
          isPrivate: this.isPrivate,
        })
        .then(() => {
          alert("등록이 완료되었습니다.");
          location.href = "/vlist";
        })
        .catch(() => {
          location.href = "/error/영상 업로드에 실패했습니다.";
        });
    },
  },
};
</script>

<style scoped>
.button1 {
  display: inline-block;
  outline: none;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  font: 14px/100% Arial, Helvetica, sans-serif;
  padding: 0.5em 2em 0.55em;
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.3);
  -webkit-border-radius: 0.5em;
  -moz-border-radius: 0.5em;
  border-radius: 0.5em;
  -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
}
.button1:hover {
  text-decoration: none;
}
.button1:active {
  position: relative;
  top: 1px;
}
.blue {
  color: #d9eef7;
  border: solid 1px #0076a3;
  background: #0095cd;
  background: -webkit-gradient(
    linear,
    left top,
    left bottom,
    from(#00adee),
    to(#0078a5)
  );
}
.blue:hover {
  background: #8bc1ee;
  background: -webkit-gradient(
    linear,
    left top,
    left bottom,
    from(#9acaf1),
    to(#6bafe7)
  );
}
.blue:active {
  color: #8bc1ee;
  background: -webkit-gradient(
    linear,
    left top,
    left bottom,
    from(#9acaf1),
    to(#6bafe7)
  );
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
.b-icon:hover {
  color: #faf3e1;
}
</style>
