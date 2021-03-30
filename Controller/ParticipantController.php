<?php

namespace App\Controller;

use App\Entity\Participant;
use App\Form\ParticipantType;
use App\Repository\ParticipantRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;




/**
 * @Route("/participant")
 */

class ParticipantController extends AbstractController
{
    /**
     * @Route("/", name="participant_index", methods={"GET"})
     * @param ParticipantRepository $participantRepository
     * @return Response
     */
    public function index(ParticipantRepository $participantRepository): Response
    { 
        
          $em= $this->getDoctrine()->getManager();
        $nbrlike=$em->getRepository(Participant::class);
        return $this->render('participant/index.html.twig', [
            'participants' => $participantRepository->findAll(),
            'nbr' => $nbrlike ,
        ]);
    }
      /**
     * @Route("/stat", name="participant_stat")
     */
    public function statistiques(ParticipantRepository $OffreEmploiRepository)
    {
        $offreStage = $OffreEmploiRepository->findAll();
        $PFE=$this->getdoctrine()->getRepository(Participant:: class)->countForStage('Stagiaire');
        $Tech=$this->getdoctrine()->getRepository(Participant :: class)->countForStage('Employé');
        $ouvrier=$this->getdoctrine()->getRepository(Participant :: class)->countForStage('Autre');
        $arrayOFcolors=["red","blue","green"];
        $array=[$PFE,$Tech,$ouvrier];
        $arrayOFnames=["Stagiaire","Employé","Autre"];
        return $this->render('participant/stat.html.twig',[
            'array' => json_encode($array),
            'arrayOFnames' => json_encode($arrayOFnames),
            'arrayOFcolors' => json_encode($arrayOFcolors)
        ]);
    }

     /**
     * @Route("/news", name="offre_emploi_news")
     */
    public function tri(ParticipantRepository $offreEmploiRepository)
    {
        $student=$this->getDoctrine()->getRepository(Participant::class)->findByASC();
        return $this->render('participant/index.html.twig', [
            'participants' => $student,
        ]);
    }
    /**
     * @Route("/new", name="participant_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $participant = new Participant();
        $form = $this->createForm(ParticipantType::class, $participant);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($participant);
            $entityManager->flush();

            return $this->redirectToRoute('participant_index');
        }

        return $this->render('participant/new.html.twig', [
            'participant' => $participant,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{id}", name="participant_show", methods={"GET"})
     */
    public function show(Participant $participant): Response
    {
        return $this->render('participant/show.html.twig', [
            'participant' => $participant,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="participant_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Participant $participant): Response
    {
        $form = $this->createForm(ParticipantType::class, $participant);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('participant_index');
        }

        return $this->render('participant/edit.html.twig', [
            'participant' => $participant,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="participant_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Participant $participant): Response
    {
        if ($this->isCsrfTokenValid('delete'.$participant->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($participant);
            $entityManager->flush();
        }

        return $this->redirectToRoute('participant_index');
    }
  

}
