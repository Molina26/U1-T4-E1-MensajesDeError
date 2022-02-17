const showToast = (title, message, type, time) => {
  new Notify({
    title: title,
    text: message,
    autoclose: true,
    status: type,
    autotimeout: time
  })
}