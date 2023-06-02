import type { RouteRecordRaw } from 'vue-router'

export const routes: Array<RouteRecordRaw> = [
  {
    path: '/elven',
    name: 'elven',
    component: () => import('@/pages/app-home/index.vue'),
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/pages/app-home/Content/HomeCenter.vue'),
      },
      {
        path: 'chat',
        name: 'chat',
        component: () => import('@/pages/chat-room/index.vue'),
      },
      {
        path: '/elven',
        redirect: {
          name: 'home',
        },
      },
    ],
  }, {
    path: '/login',
    name: 'login',
    component: () => import('@/pages/app-login/index.vue'),
  }, {
    path: '/person',
    name: 'person',
    component: () => import('@/pages/app-person/index.vue'),
    children: [
      {
        path: 'elvenInfo',
        name: 'elvenInfo',
        component: () => import('@/pages/app-person/Content/ElvenForm.vue'),
      },
      {
        path: '/person',
        redirect: {
          name: 'elvenInfo',
        },
      },
    ],
  }, {
    path: '/',
    redirect: {
      name: 'elven',
    },
  },
]
