<script setup lang='ts'>
import { useRouter } from 'vue-router';
import LVSun from '../LVIcons/LVSun.vue'
import LVMoon from '../LVIcons/LVMoon.vue'
import LVDarkToggle from '../LVDarkToggle/index.vue'
import { useLoginStore, } from '@stores'
import type { IUserInfo } from '@stores'
import { ref } from 'vue'
import Wrap from './Wrap.vue'
import { storeToRefs } from 'pinia';

withDefaults(defineProps<{
  hasHeight: boolean,
  isTransition: boolean
}>(), {
  hasHeight: true,
  isTransition: true
})

const userStore = useLoginStore()
const { getUserInfo: userInfo } = storeToRefs(userStore)

const router = useRouter()

function toPerson() {
  router.push({
    path: '/person'
  })
}

function toLogin() {
  router.push({
    path: '/login'
  })
}
const toggleBtn = ref(false)

function toRegiste() {
  router.push({
    path: '/login'
  })
}

function toHome() {
  router.push({
    path: '/elven'
  })
}

function parse(target: IUserInfo | string, key: keyof IUserInfo) {
  if (typeof target === 'string') {
    return JSON.parse(target)[key]
  }
  return target[key]
}

function toOut() {
  userStore.toOut()
}

</script>

<template>
  <div :style="hasHeight ? 'height:319px' : 'auto'">
    <Wrap :isTransition="isTransition">
      <template #default>
        <ul flex-1 px-20px items-center h-full flex>
          <li flex-1 cursor-pointer @click="toHome">Elven Home</li>
          <li border-r="1px solid" style="border-color: var(--c-html-color);" mr-5px px-10px flex items-center>
            <span text-10px ml-10px cursor-pointer @click="toPerson">个人中心</span>
            <span mx-10px>
              <img v-if="userInfo" :src="parse(userInfo, 'headPicture')" loading="lazy"
                class="account-avatar bg-base rounded-full" h-30px w-30px select-none style="clip-path: none;">
              <div v-else text-22px class="i-ri:account-circle-fill"></div>
            </span>
            <span text-10px>{{ userInfo ? parse(userInfo, 'username') : '未登录' }}</span>
          </li>
          <li h-full style="justify-content: space-around;" flex items-center>
            <LVDarkToggle v-slot="{ toggle, isDark }">
              <el-switch px-10px v-model="toggleBtn" :="isDark" @click="toggle"
                style="--el-switch-off-color: #bfc4c1; --el-switch-on-color: rgb(70, 70, 70);" :active-icon="LVMoon"
                :inactive-icon="LVSun" inline-prompt />
            </LVDarkToggle>
            <div px-10px class="i-ri:github-fill" text-20px cursor-pointer></div>
            <div v-if="!userInfo">
              <span px-10px @click="toLogin" text-sm cursor-pointer>登录</span>
              <span px-5px @click="toRegiste" text-sm cursor-pointer>注册</span>
            </div>
            <div v-else>
              <span text-sm px-5px cursor-pointer @click="toOut">退出</span>
            </div>
          </li>
        </ul>
      </template>
    </Wrap>
  </div>
</template>

<style lang='scss' scoped>
</style>
