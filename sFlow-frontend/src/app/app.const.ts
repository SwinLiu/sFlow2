/**
 * API_URL:接口地址
 * HTTPStatus:http状态
 */
export const CONSTANTS = {
  API_URL: {
    //登录地址
    login: "/api/login",
    //菜单模块地址
    user: {
      add: "/api/user/add", //添加用户信息
      list: "/api/user/list"//用户信息列表
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

