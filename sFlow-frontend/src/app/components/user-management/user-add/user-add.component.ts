import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import * as crypto from 'crypto-browserify';

@Component({
  selector: 'sFlow-user-add',
  templateUrl: './user-add.component.html'
})
export class UserAddComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
    const sha1Str: string = crypto.createHash('sha1').update('test').digest('hex');
    console.log("sha1 = %s", sha1Str);

    // const RSA_PUBLIC_KEY: string = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBi\n' +
    //   'QKBgQCoTmibtJI3k06/qq7AtGBwMRHTYN0Wdsb0ff4024OBO/2KFYamBT5oiOUNxJSC5+GS+1u5S\n' +
    //   'vRpdZV4NPsxAEF1gR/01BWD4ajQv0ZRrl6dmq9G2iUaGnTjC+0CzfdHj8SKoaP08B4frTBf+30+7m81KO7STli\n' +
    //   'KijXXdHhh81mMVQIDAQAB';

    // var key = new NodeRSA('-----BEGIN RSA PRIVATE KEY-----\n'+
    // 'MIIBOQIBAAJAVY6quuzCwyOWzymJ7C4zXjeV/232wt2ZgJZ1kHzjI73wnhQ3WQcL\n'+
    // 'DFCSoi2lPUW8/zspk0qWvPdtp6Jg5Lu7hwIDAQABAkBEws9mQahZ6r1mq2zEm3D/\n'+
    // 'VM9BpV//xtd6p/G+eRCYBT2qshGx42ucdgZCYJptFoW+HEx/jtzWe74yK6jGIkWJ\n'+
    // 'AiEAoNAMsPqwWwTyjDZCo9iKvfIQvd3MWnmtFmjiHoPtjx0CIQCIMypAEEkZuQUi\n'+
    // 'pMoreJrOlLJWdc0bfhzNAJjxsTv/8wIgQG0ZqI3GubBxu9rBOAM5EoA4VNjXVigJ\n'+
    // 'QEEk1jTkp8ECIQCHhsoq90mWM/p9L5cQzLDWkTYoPI49Ji+Iemi2T5MRqwIgQl07\n'+
    // 'Es+KCn25OKXR/FJ5fu6A6A+MptABL3r8SEjlpLc=\n'+
    // '-----END RSA PRIVATE KEY-----');
    // const encrypted1 = crypto.publicEncrypt(RSA_PUBLIC_KEY, "test").toString('hex');
    // console.log("RSA = %s", encrypted1);

    // let encrypted1 = key.encrypt("Message", 'base64');
    // console.log("RSA = %s",encrypted1);

    // let clientKey = new NodeRSA(RSA_PUBLIC_KEY)
    // let encrypted = clientKey.encrypt("Message", 'base64')
    // console.log("RSA = %s",encrypted);

  }

  goToBack() {
    this.location.back();
  }

}

