<template>
    <div>
        <transition name="fade">
            <div v-if="visible" :key="update_key">
                <div class="quas-setting-modal">
                    <div>
                        <h4>{{title}}</h4>
                        <i class="iconfont icon-guanbi" @click="close"/>
                    </div>
                    <hr/>
                    <div class="quas-modal-content">
                        <!-- modal内显示内容slot -->
                        <slot name="content"/>
                    </div>
                </div>
                <div class="quas-modal-mask" @click="close"/>
            </div>
        </transition>
    </div>
</template>

<script>
    export default {
        name: "QuasSettingModal",
        props: {
            /**
             * modal标题
             */
            title: {
                type: String,
                default: ""
            }
        },
        data() {
            return {
                visible: false,
                update_key: 0
            };
        },
        methods: {
            /**
             * 关闭modal
             * @public
             */
            close() {
                this.visible = false;
            },
            /**
             * 显示modal
             * @public
             */
            show() {
                this.visible = true;
                this.update_key++;
            }
        }
    }
</script>

<style scoped>
    i {
        position: absolute;
        top: 4px;
        right: 4px;
        transition: all 0.3s;
    }

    i:hover {
        background: white;
    }

    h4 {
        margin-left: 0.5rem;
    }
</style>