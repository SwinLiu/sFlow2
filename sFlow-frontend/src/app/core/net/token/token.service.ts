import { Injectable } from '@angular/core';
import { TokenData } from './token.type';

/** 存储键 */
const KEY = 'user_token';
/**
 * 基于Token认证，在前后端分离非常普通，本身只提供一个接口的形式展示如果优雅处理HTTP请求
 */
@Injectable()
export class TokenService {
    /**
     * 保存
     */
    set data(token: string) {
        localStorage.setItem(KEY, token);
    }

    /**
     * 获取
     */
    get data(): string {
        return localStorage.getItem(KEY) || "";
    }

    login(token: string) {
        localStorage.setItem(KEY, token);
    }

    logout() {
        localStorage.removeItem(KEY);
    }
}
