<template>
    <nav>
        <div class="quas-nav-bar">
            <ul>
                <li>
                    <img src="favicon.ico" width="32" height="32">
                    <a href="#" class="quas-nav-link">Questionnaire Analysis System</a>
                </li>
            </ul>
            <ul class="quas-nav-list">
                <li v-for="(label, index) in labels" :key="index" class="quas-nav-item">
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
            /**
             * 导航栏的标签数组
             */
            labels: {
                type: Array,
                default: () => {return [];}
            },
            /**
             * 导航栏链接数组
             */
            links: {
                type: Array,
                default: () => {return [];}
            }
        }
    }
</script>

<style scoped>

</style>