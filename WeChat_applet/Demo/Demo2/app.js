App({
  onLaunch: function (options) {
    console.log("onLaunch")
  },
  onShow: function (options) {
    console.log("onShow")
  },
  onHide: function () {
    console.log()
  },
  onError: function (msg) {
    console.log(msg)
  },
  globalData: 'I am global data'
})