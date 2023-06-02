  <!-- Mr.Liu -->
<script setup lang='ts'>
import LoginContent from './Content/index.vue'

</script>

<template>
  <div h-100vh flex w-100vw items-center justify-center>
    <div w-550px flex items-center justify-center h-650px text-20px
      style="background-color: var(--c-bg-login);border-color:var(--c-bg-tabs)" border-r="1px solid ">
      <div flex>
        <div class="i-game-icons:shut-rose" text-26px mr-10px> </div>
        <span>
          Eleven Home
        </span>
      </div>
    </div>
    <div pd-20px w-550px h-650px style="background-color: var(--c-bg-login);">
      <LoginContent></LoginContent>
    </div>
  </div>
</template>

<style lang='scss' scoped>
</style>
