<script setup>
import {ref} from 'vue'
const easyhtml=ref("java so easy")

function change(){
  easyhtml.value="好好学习，天天向上"
}
</script>

<template>
  <div v-html="easyhtml"></div>
   <button type="button"  @click="change">1</button>
</template>

<style scoped>

</style>
