/*global module:false*/
module.exports = function(grunt) {
    require('./grunt-lib/grunt-thrift')(grunt);

    // Project configuration.
    grunt.initConfig({
        thrift: {
            all: {
                languages: ['js:jquery'],
                filePaths: ['../goget.thrift'],
                out: 'build/js'
            }
        },
        less: {
            options: {
                paths: ["./less/"]
            },
            dev: {
                files: {
                    "./build/css/style.css": "./less/main.less"
                }
            },
            prod: {
                options: {
                    compress: true,
                    cleancss: true
                },
                files: {
                    "./build/css/style.css": "./less/main.less"
                }
            }
        },
        browserify: {
            all: {
                files: {
                    './build/js/main.js': ['js/**/*.js'],
                }
            }
        },
        uglify: {
            prod: {
                options: {
                    mangle: {
                        except: ['jQuery', '$', 'Backbone', 'Handlebars', '_']
                    },
                    preserveComments: false
                },
                files: [{
                    expand: true,
                    cwd: './build/js',
                    src: '**/*.js',
                    dest: './build/js'
                }]
            }
        },
        connect: {
            server: {
                options: {
                    keepalive: true,
                    port: 80,
                    base: 'build'
                }
            }
        },
        bowercopy: {
            options: {
                destPrefix: 'build'
            },
            dev: {
                files: {
                    'css': 'bootstrap/dist/css/*[!min].css*',
                    'fonts': 'bootstrap/dist/fonts/*',
                    'lib/bootstrap.js': 'bootstrap/dist/js/bootstrap.js',
                    'lib/jquery.js': 'jquery/dist/jquery.js',
                    'lib/backbone.js': 'backbone/backbone.js',
                    'lib/underscore.js': 'underscore/underscore.js',
                    'lib/handlebars.js': 'handlebars/handlebars.js',
                    'lib/thrift.js': 'bower-thrift/thrift.js'
                }
            },
            prod: {
                'css': 'bootstrap/dist/css/*[!min].css*',
                'fonts': 'bootstrap/dist/fonts/*'
            }
        },
        clean: ["build"],
        htmlbuild: {
            options: {
                beautify: true,
                relative: true,
                styles: {
                    css: 'build/css/*.css'
                }
            },
            dev: {
                src: 'index.html',
                dest: 'build',
                options: {
                    scripts: {
                        js: 'build/js/*.js',
                        lib: ['build/lib/jquery.js', 'build/lib/underscore.js', 'build/lib/*.js']
                    },
                    sections: {
                        cdnjs: [],
                        cdncss: []
                    },
                    data: {
                        restBaseUrl: 'http://127.0.0.1:8080/goget/rest/',
                        thriftBaseUrl: 'http://127.0.0.1:8080/goget/thrift'
                    }
                }
            },
            prod: {
                src: 'index.html',
                dest: 'build',
                options: {
                    scripts: {
                        js: 'build/js/*.js',
                        lib: []
                    },
                    sections: {
                        cdnjs: './includes/cdnjs.html',
                        cdncss: './includes/cdncss.html'
                    },
                    data: {
                        restBaseUrl: '/rest/',
                    }
                }
            }

        },
        handlebars: {
            options: {
                commonjs: true
            },
            dev: {
                files: {
                    "js/templates.js": "./templates/*.hbs",
                }
            },
            prod: {
                files: {
                    "js/templates.js": "./templates/*.hbs",
                }
            }
        },
        copy: {
            main: {
                files: [
                    // includes files within path
                    {
                        src: 'img/*',
                        dest: 'build/'
                    }
                ]
            }
        },
        watch: {
            gruntfile: {
                files: "Gruntfile.js",
                tasks: ["watch"]
            },
            less: {
                files: "less/**/*",
                tasks: ["less:dev"]
            },
            browserify: {
                files: "js/**/*.js",
                tasks: ["browserify"]
            },
            htmlbuild: {
                files: "*.html",
                tasks: ["htmlbuild:dev"]
            },
            handlebars: {
                files: "templates/**/*.hbs",
                tasks: ["handlebars:dev"]
            }
        },
    });
    // These plugins provide necessary tasks.
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-browserify');
    grunt.loadNpmTasks('grunt-browserify-bower');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.loadNpmTasks('grunt-bowercopy');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-html-build');
    grunt.loadNpmTasks('grunt-contrib-handlebars');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-copy');

    // Default task.
    grunt.registerTask('default', ['watch']);
    grunt.registerTask('build:prod', ['clean', 'copy', 'bowercopy:prod', 'less:prod', 'handlebars:prod', 'browserify', 'thrift', 'uglify:prod', 'htmlbuild:prod']);
    grunt.registerTask('build:dev', ['clean', 'copy', 'bowercopy:dev', 'less:dev', 'handlebars:dev', 'browserify', 'thrift','htmlbuild:dev']);
};