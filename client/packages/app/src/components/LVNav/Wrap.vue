  <!-- Mr.Liu -->
<script setup lang='ts'>
import { ref, computed, onUnmounted } from 'vue'

const props = withDefaults(defineProps<{
  isTransition?: boolean
}>(), {
  isTransition: true
})
const isShow = ref(false)

const toShow = computed(() => {
  if (props.isTransition) {
    return isShow.value
  } else {
    return true
  }
})


function listiner() {
  isShow.value = window.pageYOffset > 240
}

window.addEventListener('scroll', listiner)

onUnmounted(() => {
  window.removeEventListener('scroll', listiner)
})

</script>
  
<template>
  <transition enter-active-class="animate__animated animate__fadeIn"
    leave-active-class="animate__animated animate__fadeOutUp">
    <div v-show="toShow" :style="isTransition ? 'position:fixed;z-index:2' : ''" h-60px w-full
      style="background-color:  var(--c-bg-tabs);">
      <slot></slot>
    </div>
  </transition>
</template>
  
<style lang='scss' scoped>
</style>
  