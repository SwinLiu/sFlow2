/**
 * API_URL:接口地址
 * HTTPStatus:http状态
 */
export const CONSTANTS = {
  ROUTE_URL: {
    signin: "/login",
    user: {
      list: "/user/management/list",
      add: "/user/management/add"
    },
    company: {
      list: "/company/management/list",
      add: "/company/management/add"
    },
    employee: {
      list: "/employee/management/list",
      add: "/employee/management/add"
    }
  },
  API_URL: {
    //登录地址
    login: "/api/auth/login",
    logout: "/api/logout",
    captcha: "/api/auth/captcha",
    //菜单模块地址
    user: {
      session: "/api/user/session",
      add: "/api/user/add", //添加用户信息
      delete: "/api/user/delete",
      page: "/api/user/page",
      list: "/api/user/list"//用户信息列表
    },
    company: {
      add: "/api/company/add",
      delete: "/api/company/delete",
      list: "/api/company/list",
      page: "/api/company/page",
      select: "/api/company/select"
    },
    employee: {
      add: "/api/employee/add",
      delete: "/api/employee/delete",
      list: "/api/employee/list",
      page: "/api/employee/page"
    }
  },
  HTTPStatus: {
    SUCCESS: 200, //成功
    UNAUTHORIZED: 401,//没有权限
    INTERNAL_SERVER_ERROR: 500,//系统异常
    GATEWAY_TIMEOUT: 504,//服务器断开
    FORBIDDEN: 403//禁止访问
  }
}

