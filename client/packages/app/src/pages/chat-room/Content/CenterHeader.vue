  <!-- Mr.Liu -->
<script setup lang='ts'>

</script>

<template>
  <div style="background-color: var(--c-bg-tabs);" h-50px rounded flex items-center px-20px>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/elven' }">
        <div cursor-pointer flex items-center>
          <div cursor-pointer class="i-ri:home-4-fill" mr-5px></div>
          <span>home</span>
        </div>
      </el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/elven' }">
        <div cursor-pointer flex items-center>
          <div cursor-pointer class="i-ri:community-fill" mr-5px></div>
          <span>Community</span>
        </div>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<style lang='scss' scoped>
</style>
