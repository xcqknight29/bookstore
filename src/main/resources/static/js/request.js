(function (win) {
    const service = axios.create({
        baseURL: '/'
    })
    win.$axios = service
})(window);