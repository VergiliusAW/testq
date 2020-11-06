import styles from "../styles/Home.module.css";

function Article({content}) {
    return (
        <div className={styles.grid}>
            <a href="/post/1" className={styles.card}>
                <h3>{content.title} &rarr;</h3>
                <p className={styles.Cbody}>{content.body}</p>
                <div className={styles.Cfooter}>
                    <p className={styles.Cauthor}>{content.author} &#9866; </p>
                    <p className={styles.Cdate}>{content.date}</p>
                </div>
            </a>
        </div>
    )
}

export default Article