<template>
    <nav>
        <div class="quas-nav-band">
            <img src="favicon.ico" width="32" height="32">
            <a href="#">Questionnaire Analysis System</a>
        </div>

        <div class="quas-nav-bar">
            <ul>
                <li v-for="(label, index) in labels" :key="index">
                    <a class="quas-nav-link" :href="links[index]">{{label}}</a>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "QuasNav",
        props: {
            labels: {
                type: Array,
                required: true
            },
            links: {
                type: Array,
                required: true
            },
            active_index: {
                type: Number,
                default: 0
            }
        }
    }
</script>

<style scoped>

</style>