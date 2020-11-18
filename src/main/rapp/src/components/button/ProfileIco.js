import React from "react";
import "./ProfileIco.css";

export default function ProfileIco(props) {
    return (
        <div className={"profile-pic"}>
            <a href={"/profile/" + props.href}>
                <img src={props.img}></img>
            </a>
        </div>
    );
}
