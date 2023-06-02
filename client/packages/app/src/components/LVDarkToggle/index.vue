<script setup lang="ts">
import { context } from '.'
</script>

<template>
  <div tag="span">
    <slot v-bind="context" />
  </div>
</template>
