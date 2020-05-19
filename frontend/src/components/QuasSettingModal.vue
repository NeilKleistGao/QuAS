<template>
    <div>
        <transition name="fade">
            <div v-if="visible">
                <div>
                    <h4>{{title}}</h4>
                    <i class="iconfont icon-guanbi"/>
                </div>
                <div>
                    <slot name="content"/>
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
    export default {
        name: "QuasSettingModal",
        props: {
            visible: {
                type: Boolean,
                default: false
            },
            title: {
                type: String,
                default: ""
            }
        }
    }
</script>

<style scoped>

</style>