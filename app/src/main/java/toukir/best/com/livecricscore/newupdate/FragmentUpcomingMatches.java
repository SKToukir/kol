package toukir.best.com.livecricscore.newupdate;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import toukir.best.com.livecricscore.R;
import toukir.best.com.livecricscore.config.Api;
import toukir.best.com.livecricscore.newupdate.adapter.UpcomingMatchAdapter;
import toukir.best.com.livecricscore.utils.UpcomingMatch;

/**
 * Created by toukir on 11/18/17.
 */

public class FragmentUpcomingMatches extends Fragment {

    private RecyclerView recyclerUpComingMatches;
    private UpcomingMatchAdapter adapter;
    private List<UpcomingMatch> oldMatches = new ArrayList<>();
    private UpcomingMatch upcomingMatch;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_upcoming_matches, container, false);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please wiat..");
        dialog.show();

        recyclerUpComingMatches = (RecyclerView) view.findViewById(R.id.recyclerUpComingMatches);
        adapter = new UpcomingMatchAdapter(oldMatches, getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerUpComingMatches.setLayoutManager(mLayoutManager);
        recyclerUpComingMatches.setItemAnimator(new DefaultItemAnimator());
        recyclerUpComingMatches.setAdapter(adapter);
        recyclerUpComingMatches.setHasFixedSize(true);

        loadOldMatch(Api.OLD_MATCH_API+Api.API_KEY);

        return view;
    }

    private void loadOldMatch(String s) {
        Log.d("Response","OldMatch 0");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, s, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response","OldMatch "+response);
                dialog.dismiss();
                try {
                    JSONArray array = response.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++){

                        JSONObject obj = array.getJSONObject(i);
                        upcomingMatch = new UpcomingMatch();
                        upcomingMatch.setDescription(obj.getString("description").replaceAll("&amp;","&"));

                        oldMatches.add(upcomingMatch);

                    }

                    recyclerUpComingMatches.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response","OldMatch "+error.getMessage());
            }
        });
        Volley.newRequestQueue(getActivity()).add(request);

    }
}
