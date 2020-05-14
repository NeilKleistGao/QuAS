<template>
    <div>
        <i class="iconfont icon-xiajiantou"></i>
        <div class="quas-dropdown">
            <button v-if="result === ''">{{placeholder}}</button>
            <button v-else>{{result}}</button>
            <div class="quas-dropdown-content">
                <!--TODO:-->
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
            }
        },
        data() {
            return {
                result: ""
            };
        }
    }
</script>

<style scoped>

</style>