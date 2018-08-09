App({
  onLaunch: function (options) {
    debugger;
    console.log("触发onLaunch")
  },
  onShow: function (optiSons) {
    debugger;
    console.log("触发onShow")
  },
  onHide: function () {
    debugger;
    console.log("触发onHide")
  },
  onError: function (msg) {
    debugger;
    console.log(msg)
  },
  globalData: 'I am global data'
})