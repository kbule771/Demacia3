
<template>
  <v-carousel cycle height="312" :show-arrows="false">
    <v-carousel-item
      v-for="(item, i) in items"
      :key="i"
      :src="item.src"
    ></v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          src: require("../../assets/img/ad1.png"),
        },
        {
          src: require("../../assets/img/ad2.png"),
        },
        {
          src: require("../../assets/img/ad3.png"),
        },
      ],
    };
  },
};
</script>

<style scoped>
</style>