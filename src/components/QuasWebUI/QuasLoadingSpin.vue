<template>
    <div>
        <img src="loading.png" class="quas-loading-spin" :width="width" :height="height">
        <span v-if="label !== ''" class="quas-loading-label">
            {{label}}
            <small v-if="typing">{{dots}}</small>
        </span>
    </div>
</template>

<script>
    export default {
        name: "QuasLoadingSpin",
        props: {
            /**
             * 旋转图像下方显示的文字，默认为空
             */
            label: {
                type: String,
                default: ""
            },
            /**
             * 旋转图片的宽度，默认为128
             */
            width: {
                type: Number,
                default: 128
            },
            /**
             * 旋转图片的高度，默认为128
             */
            height: {
                type: Number,
                default: 128
            },
            /**
             * 文字后是否显示带打字机动画省略号
             */
            typing: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                dots: ""
            };
        },
        mounted() {
            if (this.typing) {
                let self = this;
                this.timer = setInterval(() => {
                    if (self.dots.length < 3) {
                        self.dots += ".";
                    }
                    else {
                        self.dots = "";
                    }
                }, 1000);
            }
        },
        beforeDestroy() {
            if (this.timer) {
                clearInterval(this.timer);
            }
        }
    }
</script>

<style scoped>
</style>