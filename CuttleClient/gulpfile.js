'use strict';

// Plugins.
var gulp        = require("gulp"),
    htmlmin     = require("gulp-html-minifier"),
    less        = require("gulp-less"),
    plumber     = require("gulp-plumber"),
    pug         = require("gulp-pug"),
    watch       = require("gulp-watch"),
    del         = require("del"),
    browserSync = require("browser-sync").create(),
    polybuild   = require("polybuild"),
    history     = require('connect-history-api-fallback');

// Config.
var src             = "src/",
    debugDest       = "debug/",
    debugPugSrc     = src + "**/*.pug",
    debugPugEnv     = {pretty: true},
    debugLessSrc    = src + "**/*.less",
    debugJsSrc      = src + "elements/**/*.js",
    debugJsDest     = debugDest + "elements",
    debugBowerSrc   = src + "bower_components/**/*",
    debugBowerDest  = debugDest + "bower_components",
    debugPort       = 5000,
    releaseDest     = "release/",
    releasePolySrc  = debugDest + "index.html",
    releasePolyEnv  = {maximumCrush: true, suffix: ""},
    releasePort     = 5001;

function server(port, dir) {
    browserSync.init({
        port: port,
        notify: false,
        // https: true,
        server: dir,
        middleware: history()
    });
}

server.reload = function(env) {
    return browserSync.reload(env);
}

function stream(plugin, src, dest, env) {
    return gulp.src(src)
        .pipe(plumber())
        .pipe(plugin(env))
        .pipe(gulp.dest(dest));
}

function watchStream(plugin, src, dest, env) {
    return gulp.src(src)
        .pipe(watch(src))
        .pipe(plumber())
        .pipe(plugin(env))
        .pipe(gulp.dest(dest))
        .pipe(server.reload({stream: true}));
}

function copy(src, dest) {
    return gulp.src(src)
        .pipe(plumber())
        .pipe(gulp.dest(dest));
}

function watchCopy(src, dest) {
    return gulp.src(src)
        .pipe(watch(src))
        .pipe(plumber())
        .pipe(gulp.dest(dest))
        .pipe(server.reload({stream: true}));
}

gulp.task("debug-pug", function() {
    return stream(pug, debugPugSrc, debugDest, debugPugEnv);
});

gulp.task("debug-less", function() {
    return stream(less, debugLessSrc, debugDest);
});

gulp.task("debug-js", function() {
    return copy(debugJsSrc, debugJsDest);
});

gulp.task("debug-bower", function() {
    return copy(debugBowerSrc, debugBowerDest);
});

gulp.task("debug-clean", function() {
    return del([debugDest]);
});

gulp.task("debug-server", function() {
    server(debugPort, debugDest);
});

gulp.task("watch-pug", function() {
    return watchStream(pug, debugPugSrc, debugDest, debugPugEnv);
});

gulp.task("watch-less", function() {
     return watchStream(less, debugLessSrc, debugDest);
});

gulp.task("watch-js", function() {
     return watchCopy(debugJsSrc, debugJsDest);
});

gulp.task("watch-bower", function() {
     return watchCopy(debugBowerSrc, debugBowerDest);
});

gulp.task("release-polybuild", ["debug"], function() {
    return stream(polybuild, releasePolySrc, releaseDest, releasePolyEnv);
});

gulp.task("release-clean", function() {
     return del([releaseDest]);
});

gulp.task("debug", ["debug-pug", "debug-less", "debug-js", "debug-bower"]);

gulp.task("release", ["release-polybuild"], function() {
    // Minify html.
    return stream(htmlmin, releaseDest + "index.html", releaseDest, {collapseWhitespace: true, removeComments: true});
});

gulp.task("default", ["debug"]);

gulp.task("clean", ["debug-clean", "release-clean"]);

// Debug serve also does watch.
gulp.task("serve", [
    "debug", "debug-server", "watch-pug", "watch-less", "watch-js", "watch-bower"
]);

// Release serve has no watch.
gulp.task("serve:release", ["release"], function() {
    // Release server.
    server(releasePort, releaseDest);
});
