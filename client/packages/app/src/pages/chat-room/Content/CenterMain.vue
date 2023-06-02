  <!-- Mr.Liu -->
<script setup lang='ts'>
import { toS, toO } from '@elven/utils'
import { useChatStore } from '@/stores';
import { useLoginStore } from '@/stores';

const loginStore = useLoginStore()
const chatStore = useChatStore()
const textarea = ref('')

let ws = new WebSocket('ws://175.178.37.103:8088')

const isConnect = ref(true)

setTimeout(() => {
  isConnect.value = false
}, 1000)
console.log(loginStore.token);



ws.onopen = function () {
  ws.send(toS({
    code: '1',
    token: loginStore.getToken
  }))
}

ws.onmessage = function (e) {
  const o = toO(e.data)
  if (o.userName)
    chatStore.historyList.push(o)
}

ws.onerror = function () {
  console.log('异常错误，断开连接');
  ws.close();
}


function toSend() {
  console.log(chatStore.targetToken, loginStore.getToken);

  ws.send(toS({
    code: 2,
    type: 1,
    targetToken: chatStore.targetToken,
    token: loginStore.getToken
  }))
  textarea.value = ''
}


</script>

<template>
  <div mt-10px flex flex-col style="background-color: var(--c-bg-tabs);" flex-1 rounded>
    <!-- 聊天内容 -->
    <div flex-1>
      <template v-if="isConnect">
        <div h-full w-full element-loading-text="加载中..." v-loading="isConnect"></div>
      </template>
      <ul v-else px-10px style="overflow: auto;height: calc(100vh - 335px);" class="da">
        <li v-for="(m, idx) in chatStore.historyList" mb-10px :key="idx" flex :style="{
          'flex-direction': m.token !== loginStore.getToken ? 'row' : 'row-reverse'
        }">
          <div>
            <img :src="m.headHeadPicture" loading="lazy" class="account-avatar bg-base rounded-full" h-48px w-48px
              select-none style="clip-path: none;">
          </div>
          <div flex-1 ml-10px flex flex-col :style="{
            alignItems: m.token !== loginStore.getToken ? 'start' : 'end',
            margin: m.token !== loginStore.getToken ? '0px 0px 0px 8px' : '0px 8px 0px 0px'
          }">
            <span text-sm mb-3px>{{ m.token !== loginStore.getToken ? m.userName : m.targetName }}</span>
            <p style="" class="information" rounded max-w-300px w-fit p-5px>
              {{ m.content }}
            </p>
          </div>
        </li>
      </ul>
    </div>
    <!-- 工具栏 -->
    <div h-35px border-t="1px solid" style="border-color: var(--c-html-color)" flex items-center>
      <span class="i-ri:emotion-fill" cursor-pointer ml-10px></span>
      <span class="i-ri:folder-4-fill" cursor-pointer ml-10px></span>
      <span class="i-ri:scissors-cut-fill" cursor-pointer ml-10px></span>
      <span class="i-ri:phone-fill" cursor-pointer ml-10px></span>
      <span class="i-ri:video-chat-fill" cursor-pointer ml-10px></span>
      <span class="i-ri:mac-fill" cursor-pointer ml-10px></span>
      <span class="i-ri:macbook-fill" cursor-pointer ml-10px></span>
    </div>
    <!-- 输入框 -->
    <div flex flex-col h-180px>
      <div flex-1>
        <el-input v-model="textarea" maxlength="1000" placeholder="请输入内容....." show-word-limit type="textarea" />
      </div>
      <div h-40px style="background-color: var(--c-bg-tabs);" flex items-center justify-end pr-10px>
        <el-button type="warning" size="small" plain @click="toSend">发送</el-button>
      </div>
    </div>
  </div>
</template>

<style lang='scss' scoped>
:deep(.el-textarea) {
  height: 100%;
  background-color: var(--c-bg-tabs);

  .el-textarea__inner {
    height: 100%;
    background-color: var(--c-bg-tabs);
    box-shadow: none;
    resize: none;
  }

  .el-input__count {
    background-color: var(--c-bg-tabs);
  }
}

.da {
  height: calc(100vh - 1000px);
}

:deep(.el-loading-mask) {
  background-color: var(--c-loading);

  .circular {
    circle {
      stroke: var(--c-primary);
    }
  }

  .el-loading-text {
    color: var(--c-primary)
  }
}

.information {
  background-color: var(--c-bg-tabs);
  border: 1px solid var(--c-html-color);
  // hyphens: auto;
  white-space: pre-wrap;
  word-wrap: break-word;
  word-break: break-all;
}
</style>
