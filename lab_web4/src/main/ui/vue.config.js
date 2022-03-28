module.exports = {
    devServer: {
        proxy: {
            "^/api/": {
                target: "http://localhost:8080/lab_web4-1.0/",
                pathRewrite: {
                    "^/api/": "/"
                },
                changeOrigin: true,
                logLevel: "debug"
            }
        }
    }
};