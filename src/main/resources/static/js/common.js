function submitData(method, url, data) {
    // if(data == null) {
    //   return $axios({
    //     method: method,
    //     url: url
    //   })
    // } else {
    return $axios({
        method: method,
        url: url,
        data: data
    })
    // }
}

function submitGet(method, url, params) {
    return $axios({
        method: method,
        url: url,
        params: params
    })
}