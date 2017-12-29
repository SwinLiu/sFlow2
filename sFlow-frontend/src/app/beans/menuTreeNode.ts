export class MenuTreeNode {
  menuId: string;
  text: string;
  translate: string;
  group: number;
  link: string;
  icon: string;
  parentId: string;
  authorityId: string;
  orderNumber: number;
  deep: number;
  children: any;
  up: boolean;
  down: boolean;
  editable: boolean;
  deleteable: boolean;
  addChildFlag: boolean;
}
