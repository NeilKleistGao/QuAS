<template>
    <div class="quas-tool-bar">
        <div v-for="item in icons" :key="item">
            <img class="quas-tool-icon" :src="item + '.png'" width="32" height="32" @click="callback(item)">
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasSideTools",
        data() {
            return {
                icons: ["none", "radio", "check", "text", "rich", "drop", "date", "sort"]
            };
        },
        methods: {
            callback(label) {
                this.$emit("callback", label);
            }
        }
    }
</script>

<style scoped>

</style>