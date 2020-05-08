<template>
    <div>
        <div v-if="!editable">
            <i class="iconfont icon-wuxupailie" />
            <label :for="label"> {{label}} </label>
        </div>
        <div v-else>
            <i class="iconfont icon-wuxupailie" />
            <input class="quas-design-text" type="text" v-model="text">
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasSortItem",
        props: {
            editable: {
                type: Boolean,
                default: false
            },
            label: {
                type: String
            },
            value: {
                type: String
            },
            index: {
                type: Number,
                default: -1
            }
        },
        data() {
            return {
                text: this.value
            };
        },
        watch: {
            text(new_value) {
                this.$emit("input", new_value);
            }
        }
    }
</script>

<style scoped>

</style>