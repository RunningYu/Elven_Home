<script lang='ts' setup>
import { useRouter } from 'vue-router'
import { useLoginStore } from '@stores'

const loginStore = useLoginStore()

const passowrd = ref('')
const studentId = ref('')
const isRemember = ref(true)
const router = useRouter()


function toHome() {
    router.push({
        path: '/elven'
    })
}

function toLogin() {
    loginStore.toLogin({
        studentId: studentId.value,
        password: passowrd.value,
    })
    router.push({
        path: '/elven',
    })
}
</script>
<template>
    <div h-full w-full py-25px px-30px class="login-container">
        <div>
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <div flex items-center cursor-pointer @click="toHome">
                        <div text-25px class="i-ri:home-4-fill"></div>
                        <span text-16px ml-5px>Home</span>
                    </div>
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div flex justify-between items-center my-20px>
            <spant text-24px style="color:var(--c-home-content-title)">登录</spant>
            <span text-10px cursor-pointer>没有账号? <span style="color:var(--c-home-content-title)">点击注册</span></span>
        </div>
        <ul>
            <li mb-20px h-45px w-full>
                <el-input style="height: 100%;" v-model="studentId" placeholder="学号" clearable />
            </li>
            <li h-45px w-full>
                <el-input style="height: 100%;" v-model="passowrd" type="password" placeholder="请输入密码" show-password />
            </li>
            <li my-10px>
                <el-checkbox v-model="isRemember">记住我</el-checkbox>
            </li>
            <li h-45px w-full>
                <el-button type="warning" style="width: 100%;height: 100%;" @click="toLogin">登陆</el-button>
            </li>
            <li mt-20px text-sm text-center cursor-pointer>已有账号，<span style="color:var(--c-home-content-title)">忘记密码？</span>
            </li>
        </ul>
        <div text-center mt-60px text-22px>Welcome To Elven Home</div>
        <div mt-80px>
            <el-divider style="margin-bottom: 35px;">其他方式登陆</el-divider>
        </div>
        <ul flex text-28px justify-between>
            <li>
                <div class="i-ri:qq-fill"></div>
            </li>
            <li>
                <div class="i-ri:wechat-fill"></div>
            </li>
            <li>
                <div class="i-ri:twitter-fill"></div>
            </li>
            <li>
                <div class="i-ri:github-fill"></div>
            </li>
            <li>
                <div class="i-ri:gitlab-fill"></div>
            </li>
        </ul>
    </div>
</template>


<style lang='scss' scoped>
.login-container {
    :deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
        background-color: var(--c-primary);
        border-color: var(--c-primary);
    }



    :deep(.el-checkbox__input .el-checkbox__inner) {
        &:hover {
            border-color: var(--c-primary);
        }
    }


    :deep(.el-input__wrapper.is-focus) {
        --el-input-focus-border-color: var(--c-primary);
    }

    :deep(.el-checkbox__label) {
        color: var(--c-home-content-title);
    }

    :deep(.el-button) {
        --el-button-bg-color: var(--c-primary);
    }
}
</style>
