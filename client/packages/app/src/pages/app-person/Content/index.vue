  <!-- Mr.Liu -->
<script setup lang='ts'>
import PersonTabs from './PersonTabs.vue'
import LayContent from '@/layouts/LayContent.vue'
</script>

<template>
  <LayContent>
    <!-- 侧导 -->
    <template #content-left>
      <PersonTabs />
    </template>
    <!-- 内容 -->
    <template #content-center>
      <RouterView />
    </template>
  </LayContent>
</template>

<style lang='scss' scoped>
</style>
