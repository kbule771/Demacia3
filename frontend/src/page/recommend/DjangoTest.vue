<template>
  <div>
    안녕
  </div>
</template>

<script>
import axios from 'axios'
export default {
  created() {
    this.test()
  },
  methods: {
    test() {
      axios.get('http://j3a304.p.ssafy.io:8181/django/api/videoposts/')
      .then(res => {
        console.log(res.data)
      })
      .catch(err => console.log(err.response))
    }

  }
}
</script>

<style scoped>

</style>