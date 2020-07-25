<template>
    <div>
        <button style="margin-left:5%; visibility: hidden"/>
        <button v-for="(label, index) in labels" :key="index" :class="getButtonStyle(index)" @click="select(index)">{{label}}</button>
        <div class="quas-tab-content">
            <div v-for="(label, index) in labels" :key="index">
                <slot :name="label" v-if="current === index"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasTab",
        props: {
            labels: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                current: 0
            };
        },
        methods: {
            getButtonStyle(index) {
                if (index === this.current) {
                    return "quas-tab-button-selected";
                }
                else {
                    return "quas-tab-button";
                }
            },
            select(index) {
                this.current = index;
            }
        }
    }
</script>

<style scoped>

</style>