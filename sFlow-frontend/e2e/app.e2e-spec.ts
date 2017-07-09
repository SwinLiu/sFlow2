import { SflowUiPage } from './app.po';

describe('sflow-ui App', () => {
  let page: SflowUiPage;

  beforeEach(() => {
    page = new SflowUiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
