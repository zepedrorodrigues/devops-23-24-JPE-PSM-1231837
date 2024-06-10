const path = require('path');

module.exports = {
    entry: './src/main/js/app.js',
    devtool: 'source-map',  // Correct devtool configuration
    cache: true,
    mode: 'development',
    output: {
        path: path.resolve(__dirname, 'src/main/resources/static/built'),
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react']
                    }
                }
            }
        ]
    }
};
