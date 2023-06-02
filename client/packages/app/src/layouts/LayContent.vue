  <!-- Mr.Liu -->
<script setup lang='ts'>
withDefaults(defineProps<{
  maxw: string
  minw: string
}>(), {
  maxw: '80%',
  minw: '1000px',
})
</script>

<template>
  <div m-auto :style="{ maxWidth: maxw, minWidth: minw }" flex class="content-container">
    <slot name="content-left" />
    <slot name="content-center" />
    <!-- <slot name="content-right" />
    <slot name="default" /> -->
  </div>
</template>

<style lang='scss' scoped>
</style>
