package base.no.states.requests;

import base.no.states.DirectoryAreas;
import base.no.states.Area;
import org.json.JSONObject;

public class RequestChildren implements Request {
    //private static Area lastAreaVisited;
    private String areaId;
    private Area area;
    private JSONObject jsonTree; // 1 level tree, root and children

    public RequestChildren(String areaId) {
        this.areaId = areaId;
    }

    public void setRequestChildren(String areaID){
         this.areaId = areaID;
    }

    public String getAreaId() {
        return areaId;
    }

    @Override
    public JSONObject answerToJson() {
        return jsonTree;
    }

    @Override
    public String toString() {
        return "RequestChildren{areaId=" + areaId + "}";
    }

    public void process() {
        DirectoryAreas directoryAreas = new DirectoryAreas();
        area = DirectoryAreas.findAreaById(areaId, area);
        jsonTree = area.toJson(1);
    }
}