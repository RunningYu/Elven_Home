<template>
  <div style="background-color: var(--c-bg-tabs)" flex-1 p-20px>
    <el-form :model="form" :inline="true" label-width="120px">
      <el-form-item label="名称">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="性格">
        <el-input v-model="form.personality" />
      </el-form-item>
      <el-form-item label="状况">
        <el-input v-model="form.health" />
      </el-form-item>
      <el-form-item label="外貌">
        <el-input v-model="form.appearances" />
      </el-form-item>
      <el-form-item label="属地">
        <el-input v-model="form.collegeName" />
      </el-form-item>
      <el-form-item label="常出没地">
        <el-input v-model="form.place" />
      </el-form-item>
      <el-form-item label="第一次目击">
        <el-input v-model="form.firstFindPlace" />
      </el-form-item>
      <el-form-item label="种类">
        <el-select v-model="form.kind" placeholder="请选择种类">
          <el-option v-for="o in elvenStore.elvenTypes" :label="o" :value="o"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.sex" placeholder="请选择性别">
          <el-option v-for="o in ['雌性', '雄性']" :label="o" :value="o"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <!-- 描述 -->
    <el-form-item label="精灵描述">
      <el-input v-model="form.more" h-100px type="textarea" />
    </el-form-item>
    <!-- 上传图片 -->
    <el-form-item label="上传图片">
      <el-upload v-model:file-files="form.links" :on-success="onSuccess" :auto-upload="true"
        action="http://175.178.37.103:3188/uploadPicture" :limit="5" :on-preview="handlePictureCardPreview"
        list-type="picture-card">
        <el-icon>
          <Plus />
        </el-icon>
      </el-upload>
    </el-form-item>
    <el-dialog v-model="dialogVisible">
      <img w-full :src="dialogImageUrl" alt="Preview Image" />
    </el-dialog>
    <!-- 发布 -->
    <div flex justify-end>
      <el-button type="warning" @click="onSubmit">发送</el-button>
      <el-button>取消</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { useElvenTypes } from '@/stores'
import type { UploadProps, UploadUserFile } from 'element-plus'

const elvenStore = useElvenTypes()
elvenStore.getElvenTypes()


const onSuccess = (res: any) => {
  if (res.code === 200){
    form.links.push(res.data.minIoUrl)
  }
}

const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url!
  dialogVisible.value = true
}

const form = reactive<{
  name: string,
  personality: string
  health: string,
  appearances: string
  collegeName: string
  place: string
  more: string
  firstFindPlace: string
  kind: string
  sex: string
  files: UploadUserFile[]
  links: string[]
}>({
  name: '',
  personality: '',
  health: '',
  appearances: '',
  collegeName: '',
  place: '',
  more: '',
  firstFindPlace: '',
  kind: '',
  sex: '',
  files: [],
  links: []
})

const onSubmit = () => {
  elvenStore.toPublishElvenInfo(form as any)
}
// const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
//   console.log(uploadFile, uploadFiles)
// }

const dialogImageUrl = ref('')
const dialogVisible = ref(false)

</script>


<style scoped lang="scss">
:deep(.el-form-item__label) {
  color: var(--c-primary);
}
</style>
 