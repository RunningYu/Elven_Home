  <!-- Mr.Liu -->
<script setup lang='ts'>
// do not use same name with ref
import { useHomeStore } from '@/stores'

const homeStore = useHomeStore()

const props = withDefaults(defineProps<{
  item: {
    kind: string
    appearances: string
    sex: string
    personality: string
    health: string
    collegeName: string
    firstFindPlace: string
    name: string
    place: string
    more: string
    links: string[]
    elvesId: string
    haveLike: number
    likeNumber: number
  },
  showData: boolean
}>(), {
  showData: false
})

const formInline = reactive({
  user: '',
  region: '',
})


const s = '不怕人，爱欺负公猫，但不欺负母猫，以前住菊轩现在到处玩，征战四方，健康情况: 以前有很严重的口炎，现在已治愈。但为了不再复发，请同学们不要喂食湿粮。PS: 2022年5月口炎复发'
const list: {
  dec: string
  type: string
}[] = [{
  type: 'sex',
  dec: '性别'
}, {
  dec: '性格',
  type: 'personality'
}, {
  dec: '状况',
  type: 'health'
}, {
  dec: '属地',
  type: 'collegeName'
}, {
  dec: '常出没地',
  type: 'place'
}, {
  dec: '第一次目击',
  type: 'firstFindPlace'
}]

const infoToggel = ref<boolean>(true)


async function toLike() {
  const { haveLike, likeNumber } = await homeStore.toLike(props.item.elvesId)
  props.item.haveLike = haveLike
  props.item.likeNumber = likeNumber
}
</script>

<template>
  <div v-if="!showData">
    <el-skeleton :rows="5" animated style="--el-skeleton-circle-size: 50px">
      <template #template>
        <el-skeleton-item variant="circle" />
      </template>
    </el-skeleton>
    <br>
    <el-skeleton :rows="5" animated mb-15px />
  </div>
  <article v-else class="ariticles-content" mt-15px box-content flex border-b="solid 1.5px"
    style="border-color: var(--c-html-color);">
    <div class="author" w-70px>
      <div class="rounded-full" h-54px w-54px flex items-center justify-center style="clip-path: none;">
        <img width="400" height="400" src="../../../assets/app-home/1.jpg" alt="Akryum 的头像" loading="lazy"
          class="account-avatar bg-base rounded-full" h-48px w-48px select-none style="clip-path: none;">
      </div>
    </div>
    <div class="content" flex-1>
      <!-- 标题 -->
      <el-divider content-position="right" class="device">
        种类：{{ item.kind }}
      </el-divider>
      <!-- 外貌 -->
      <div mb-10px text-xs>
        外貌：{{ item.appearances }}
      </div>
      <!-- 昵称 -->
      <div my-5px flex items-center justify-between>
        <span class="content-rich" dir="auto" style="color:var(--c-home-content-title)" line-clamp-1 ws-pre-wrap break-all
          font-bold> {{ item.name }}
        </span>
        <ul flex items-center>
          <time datetime="2023-05-16T13:56:30.223Z" mr-5px ws-nowrap text-sm text-xs hover:underline>16 小时</time>
          <li h-30px w-30px flex items-center justify-center>
            <div class="v-popper v-popper--theme-dropdown dark" flex-none>
              <button hover="text-primary" w-fit flex select-none rounded transition-all focus:outline-none
                focus-visible="text-primary" class="text-secondary" aria-label="更多">
                <div class="v-popper v-popper--theme-tooltip">
                  <div rounded-full>
                    <div class="i-ri:more-line" />
                  </div>
                </div>
              </button>
            </div>
          </li>
        </ul>
      </div>
      <!-- 信息 -->
      <div class="box" mb-10px w-full flex py-5px pl-35px text-sm>
        <el-form :inline="true" :model="formInline" label-width="90px">
          <el-form-item v-for="i in list" :key="i" :label="i.dec">
            <div border-b="1px solid" style="border-color: var(--c-html-color);width: 180px;">
              {{ (item as any)[i.type] }}
            </div>
          </el-form-item>
        </el-form>
      </div>
      <!-- 介绍 -->
      <div text-sm>
        <p mb-5px class="more" :style="{
          '-webkit-box-orient': infoToggel ? 'vertical' : 'horizontal',
        }">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          {{
            item.more
          }}
        </p>
        <sapn v-if="s.length > 203" float-right style="cursor: pointer;color:var(--c-primary)"
          @click="infoToggel = !infoToggel">
          {{ infoToggel ? '展开' : '收起' }}
        </sapn>
      </div>
      <!-- 图片展示 -->
      <div style="width: 70%;" my-10px h-200px flex>
        <div style="width: 50%" mr-10px flex items-center>
          <el-image style="width: 100%; height: 100%" rounded :src="item.links[0]" fit="cover" />
        </div>
        <div flex flex-1 flex-wrap justify-between rounded>
          <el-image v-for="img in item.links.slice(1)" :key="img" style="width: 48%;height: 47%;" rounded :src="img"
            fit="cover" />
        </div>
        <div />
      </div>
      <!-- 按钮 -->
      <div my-10px w-full flex items-center justify-between class="status-actions">
        <div flex-1>
          <button hover="text-blue" w-fit flex select-none items-center gap-1 rounded transition-all focus:outline-none
            class="text-secondary" aria-label="回复">
            <div class="v-popper v-popper--theme-tooltip">
              <div rounded-full p1>
                <div class="i-ri:chat-1-line"></div>
              </div>
            </div>
            <div of-hidden h="1.25rem" text-sm>
              <div flex="~ col" transition-transform duration-300 class="translate-y-0">
                <span text-secondary-light /><span class="text-blue" />
              </div>
            </div>
          </button>
        </div>
        <div flex-1>
          <button hover="text-green" w-fit flex select-none items-center gap-1 rounded transition-all focus:outline-none
            class="text-secondary" aria-label="转发">
            <div class="v-popper v-popper--theme-tooltip">
              <div rounded-full p1>
                <div class="i-ri:repeat-line"></div>
              </div>
            </div>
            <div of-hidden h="1.25rem" text-sm>
              <div flex="~ col" transition-transform duration-300 class="translate-y-0">
                <span text-secondary-light /><span class="text-green" />
              </div>
            </div>
          </button>
        </div>
        <div flex-1>
          <button hover="text-rose" @click="toLike" w-fit flex select-none items-center gap-1 rounded transition-all
            focus:outline-none class="text-secondary" aria-label="喜欢">
            <div flex items-center class="v-popper v-popper--theme-tooltip">
              <div rounded-full p1>
                <div :text="item.haveLike === 1 ? 'rose' : 'none'" class=" i-ri:heart-3-line" />
              </div>
              <span text-10px>{{ item.likeNumber }}</span>
            </div>
            <div of-hidden h="1.25rem" text-sm>
              <div flex="~ col" transition-transform duration-300 class="translate-y-0">
                <span text-secondary-light>
                  <span class="flex gap-x-1">
                  </span>
                </span>
                <span class="text-rose">
                  <span class="flex gap-x-1">
                  </span>
                </span>
              </div>
            </div>
          </button>
        </div>
        <div flex-none>
          <button hover="text-yellow" w-fit flex select-none items-center gap-1 rounded transition-all focus:outline-none
            class="text-secondary" aria-label="收藏">
            <div class="v-popper v-popper--theme-tooltip">
              <div rounded-full p1>
                <div class="i-ri:bookmark-line" />
              </div>
            </div>
          </button>
        </div>
      </div>
    </div>
  </article>
</template>

<style lang='scss' scoped>
.more {
  display: -webkit-box;
  -webkit-line-clamp: 4;
  overflow: hidden;
}

.detail-title {
  color: var(--c-primary);
}

.device {
  border-top: 2px solid var(--c-bg-tabs);
  margin: 24px 0 10px 0;

  :deep(.el-divider__text) {
    color: var(--c-primary);
    background-color: var(--c-bg-tabs);
  }

}

.box {
  border-radius: .3em;
  position: relative;
  background-color: var(--c-bg-tabs);

  :deep(.el-form-item__label) {
    color: var(--c-primary);
  }
}

.box::before {
  content: '';
  position: absolute;
  right: 0;
  top: -0.85rem;
  width: 1.15em;
  height: 2em;
  background: linear-gradient(to left bottom, transparent 50%, rgba(0, 0, 0, 0.3) 0, rgba(0, 0, 0, 0.4)) 100% 0 no-repeat;
  transform: rotate(-30deg);
  transform-origin: bottom right;
  /* 让三角形的右下角成为旋转的中心 */
  box-shadow: -.2em .2em .3em -.1em rgba(0, 0, 0, .15);
  border-bottom-left-radius: inherit;
  /* 左下角继承border-radius */
}
</style>
