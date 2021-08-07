const path = require('path');

module.exports = {
	mode: "development",
	entry: './src/main/webapp/assets/js/app.js',
	output: {
		path: path.resolve(__dirname, "dist"),
		filename: 'app.bundle.js',
	},
	module: {
  		rules: [
    		{
      			test: /\.m?js$/,
      			exclude: [/(node_modules|bower_components)/,
						/node_modules[\/]core-js/,
      				/node_modules[\/]webpack[\/]buildin/,
				],
      			use: {
        			loader: 'babel-loader',
        			options: {
         				 presets: ['@babel/preset-env']
       				}
      			}
    		}
  		]
	}

}