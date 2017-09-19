import {Location} from '@angular/common';
import {Component, NgModule, OnInit} from '@angular/core';
import * as crypto from 'crypto-browserify';

declare const Buffer;

@Component({
  selector: 'sFlow-user-add',
  templateUrl: './user-add.component.html'
})
export class UserAddComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit() {
    const sha1Str: string = crypto.createHash('sha1').update('test').digest('hex');
    console.log("sha1 = %s", sha1Str);

    const RSA_PUBLIC_KEY: string = '-----BEGIN PUBLIC KEY-----\n' +
    'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOkvtlnW0AZsuC4PCiaagn4tvCKxgHDi\n' +
    'ttSM+3eOqmff7uCNb4A5MBpq6jJq2L7giQisp0ko1s1l4zchHAjN+fMCAwEAAQ==\n' +
    '-----END PUBLIC KEY-----';

    const RSA_PRIVATE_KEY = '-----BEGIN PRIVATE KEY-----\n' +
    'MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEA6S+2WdbQBmy4Lg8K\n' +
    'JpqCfi28IrGAcOK21Iz7d46qZ9/u4I1vgDkwGmrqMmrYvuCJCKynSSjWzWXjNyEc\n' +
    'CM358wIDAQABAkEAiCddt9Mfn8C3Qd1yNdwduYMGyNMPhdo6mpy+764TfZY7m5Ix\n' +
    'aAORc8gcxAa4HCgppFZKPMhVHQeQ9PPOyfTNUQIhAP53gQ6dhpoOQJph9EQI3pbw\n' +
    '+s8Zn2PRuK2qJJGfXHPvAiEA6pdifFp0p0/l2xzhRbWeJTpIU2UpBR++nd8N77od\n' +
    'Rj0CICWmxwjDlCP9Ud/F+J+MdGr/Ew1LrELXyCyiDTEi1EovAiAtd5XRAD8nxmaI\n' +
    'ZzqKJj82e+tUroCay6JIOtkao3nVCQIgXhPtwxga9IRKEmPYPPInnjslTW6+OoLA\n' +
    'LFHMeUwQnvE=\n' +
    '-----END PRIVATE KEY-----';

    const encrypted = crypto.publicEncrypt(RSA_PUBLIC_KEY, new Buffer("test")).toString('hex');
    console.log("RSA encrypted = %s", encrypted);
    const decrypted = crypto.privateDecrypt(RSA_PRIVATE_KEY, new Buffer(encrypted, 'hex'));
    console.log("RSA decrypted = %s", decrypted.toString());

  }

  goToBack() {
    this.location.back();
  }

}

