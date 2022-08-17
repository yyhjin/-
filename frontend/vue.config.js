const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
devServer: {
    proxy: {
        '/be': {

            target: 'https://i7a602.p.ssafy.io:8080' // 개발서버

        }
    }
}



})
