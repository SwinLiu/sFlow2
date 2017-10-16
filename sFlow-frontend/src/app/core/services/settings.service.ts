import { Injectable } from '@angular/core';
import { LocalStorageService } from 'angular-web-storage';
import { ThemeType } from './themes.service';

const KEY = 'layout';
const USER_SESSION = 'user_session';

export interface User {
    userId?: string;
    userName?: string;
    jwtToken?: string;
    email?: string;
    [key: string]: any;
}

export interface App {
    name?: string;
    description?: string;
    year?: number;
    [key: string]: any;
}

export type SidebarThemeType = 'light' | 'dark';

export interface Layout {
    /** 是否固定顶部菜单 */
    fixed: boolean;
    /** 是否折叠右边菜单 */
    collapsed: boolean;
    /** 是否固定宽度 */
    boxed: boolean;
    /** 当前主题 */
    theme: ThemeType;
    /** 语言环境 */
    lang: string;
}

@Injectable()
export class SettingsService {
    app: App = {
        year: (new Date()).getFullYear()
    };

    private _user: User = null;

    get user(): User {
        if (!this._user) {
            this._user = Object.assign(<User>{
                userId : '',
                userName : '',
                jwtToken : '',
                email : ''
            }, this.local.get(USER_SESSION));
            this.local.set(USER_SESSION, this._user);
        }
        return this._user;
    }

    private _layout: Layout = null;

    get layout(): Layout {
        if (!this._layout) {
            this._layout = Object.assign(<Layout>{
                fixed: true,
                collapsed: false,
                boxed: false,
                theme: 'A',
                lang: null
            }, this.local.get(KEY));
            this.local.set(KEY, this._layout);
        }
        return this._layout;
    }

    setLayout(name: string, value: any): boolean {
        if (typeof this.layout[name] !== 'undefined') {
            this.layout[name] = value;
            this.local.set(KEY, this._layout);
            return true;
        }
        return false;
    }

    setApp(val: App) {
        this.app = Object.assign(this.app, val);
    }

    setUser(val: User) {
        if (!this._user) {
            this.local.set(USER_SESSION, Object.assign(<User>{
                userId : '',
                userName : '',
                jwtToken : '',
                email : ''
            }, val));
            return true;
        }
        return false;
    }

    constructor(private local: LocalStorageService) { }
}
