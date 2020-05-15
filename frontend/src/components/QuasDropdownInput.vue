<template>
    <div>
        <i class="iconfont icon-xiajiantou"></i>
        <div class="quas-dropdown">
            <button v-if="result === ''" @click="fold">{{placeholder}}</button>
            <button v-else @click="fold">{{result}}</button>
            <div class="quas-dropdown-content" :style="{display: content_display}">
                <a href="#" v-for="item in items" :key="item" @click="select(item)">{{item}}</a>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasDropdownInput",
        props: {
            value: {
                type: String,
                required: true
            },
            placeholder: {
                type: String,
                default: "..."
            },
            items: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                result: this.value,
                visible: false,
                content_display: "none"
            };
        },
        methods: {
            select(new_value) {
                this.result = new_value;
                this.fold(false);
            },
            fold(inv = true) {
                if (inv) {
                    this.visible = !this.visible;

                    if (this.visible) {
                        this.content_display = "block";
                    }
                    else {
                        this.content_display = "none";
                    }
                }
                else {
                    this.visible = false;
                    this.content_display = "none";
                }
            }
        },
        watch: {
            result(new_value) {
                this.$emit("input", new_value);
            }
        }
    }
</script>

<style scoped>
    i {
        margin-left: 0.3rem;
        margin-right: 0.5rem;
    }
</style>